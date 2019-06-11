/*
 * Written from 2015-2019 by Daniel Saukel
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Note that DRE2N plugins are usually designed to have just one instance of DRECommandCache.
 * One instance of DRECommandCache represents one command and contains all of its subcommands.
 *
 * @author Daniel Saukel
 */
public class DRECommandCache {

    private String label;
    private CommandExecutor executor;
    private Set<DRECommand> commands = new HashSet<>();

    public DRECommandCache(String label, DREPlugin plugin, Set<DRECommand> commands) {
        this.label = label;
        this.executor = new DRECommandExecutor(plugin);
        this.commands = commands;
    }

    public DRECommandCache(String label, DREPlugin plugin, DRECommand... command) {
        this.label = label;
        this.executor = new DRECommandExecutor(plugin);
        this.commands = new HashSet<>(Arrays.asList(command));
    }

    public DRECommandCache(String label, CommandExecutor executor, Set<DRECommand> commands) {
        this.label = label;
        this.executor = executor;
        this.commands = commands;
    }

    public DRECommandCache(String label, CommandExecutor executor, DRECommand... command) {
        this.label = label;
        this.executor = executor;
        this.commands = new HashSet<>(Arrays.asList(command));
    }

    /**
     * @param commandName usually the first command argument
     * @return the command with the given name
     */
    public DRECommand getCommand(String commandName) {
        for (DRECommand command : commands) {
            if (command.getCommand().equalsIgnoreCase(commandName) || command.getAliases().contains(commandName)) {
                return command;
            }
        }

        return null;
    }

    /**
     * @return the command label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the commands
     */
    public Set<DRECommand> getCommands() {
        return commands;
    }

    /**
     * @param command the command to add
     */
    public void addCommand(DRECommand command) {
        commands.add(command);
    }

    /**
     * @param command the command to remove
     */
    public void removeCommand(DRECommand command) {
        commands.remove(command);
    }

    /**
     * @param plugin the plugin that registers the command.
     */
    public void register(JavaPlugin plugin) {
        plugin.getCommand(label).setExecutor(executor);
    }

}
