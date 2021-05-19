/*
 * Written from 2015-2021 by Daniel Saukel
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software
 * to the public domain worldwide.
 *
 * This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package de.erethon.commons.command;

import de.erethon.commons.javaplugin.DREPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Note that DRE2N plugins are usually designed to have just one instance of DRECommandCache.
 * One instance of DRECommandCache represents one command and contains all of its subcommands.
 *
 * @author Daniel Saukel, Fyreum
 */
public class DRECommandCache extends CommandCache implements TabCompleter {

    private final String label;
    private final CommandExecutor executor;
    private boolean tabCompletion = true;

    public DRECommandCache(String label, DREPlugin plugin, Set<DRECommand> commands) {
        super(commands);
        this.label = label;
        this.executor = new DRECommandExecutor(plugin);
    }

    public DRECommandCache(String label, DREPlugin plugin, DRECommand... commands) {
        super(commands);
        this.label = label;
        this.executor = new DRECommandExecutor(plugin);
    }

    public DRECommandCache(String label, CommandExecutor executor, Set<DRECommand> commands) {
        super(commands);
        this.label = label;
        this.executor = executor;
    }

    public DRECommandCache(String label, CommandExecutor executor, DRECommand... commands) {
        super(commands);
        this.label = label;
        this.executor = executor;
    }

    /**
     * @return the command label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return true if TabCompletion is enabled, false otherwise
     */
    public boolean isTabCompletion() {
        return tabCompletion;
    }

    /**
     * @param tabCompletion the boolean to set
     */
    public void setTabCompletion(boolean tabCompletion) {
        this.tabCompletion = tabCompletion;
    }

    /**
     * @param plugin the plugin that registers the command.
     */
    public void register(JavaPlugin plugin) {
        plugin.getCommand(label).setExecutor(executor);
        if (tabCompletion) {
            plugin.getCommand(label).setTabCompleter(this);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command unused1, String unused2, String[] args) {
        List<String> completes = new ArrayList<>();
        String cmd = args[0];

        if(args.length == 1) {
            List<String> cmds = new ArrayList<>();
            for (DRECommand command : commands) {
                if (command.senderHasPermissions(sender)) {
                    cmds.add(command.getCommand());
                }
            }
            for(String string : cmds) {
                if(string.toLowerCase().startsWith(cmd.toLowerCase())) {
                    completes.add(string);
                }
            }
            return completes;
        }
        for (DRECommand command : commands) {
            if (command.matches(cmd)) {
                completes.addAll(command.tabComplete(sender, args));
            }
        }
        return completes;
    }
}
