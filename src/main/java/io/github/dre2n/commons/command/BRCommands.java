/*
 * Copyright (C)2015-2016 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.commons.command;

import io.github.dre2n.commons.javaplugin.BRPlugin;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Note that DRE2N plugins are usually designed to have just one instance of BRCommands.
 * One instance of BRCommands represents one command and contains all of its subcommands.
 *
 * @author Daniel Saukel
 */
public class BRCommands {

    private String label;
    private CommandExecutor executor;
    private Set<BRCommand> commands = new HashSet<>();

    public BRCommands(String label, BRPlugin plugin, Set<BRCommand> commands) {
        this.label = label;
        this.executor = new BRCommandExecutor(plugin);
        this.commands = commands;
    }

    public BRCommands(String label, BRPlugin plugin, BRCommand... command) {
        this.label = label;
        this.executor = new BRCommandExecutor(plugin);
        this.commands = new HashSet<>(Arrays.asList(command));
    }

    public BRCommands(String label, CommandExecutor executor, Set<BRCommand> commands) {
        this.label = label;
        this.executor = executor;
        this.commands = commands;
    }

    public BRCommands(String label, CommandExecutor executor, BRCommand... command) {
        this.label = label;
        this.executor = executor;
        this.commands = new HashSet<>(Arrays.asList(command));
    }

    /**
     * @param command
     * usually the first command argument
     */
    public BRCommand getCommand(String commandName) {
        for (BRCommand command : commands) {
            if (command.getCommand().equals(commandName)) {
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
    public Set<BRCommand> getCommands() {
        return commands;
    }

    /**
     * @param command
     * the command to add
     */
    public void addCommand(BRCommand command) {
        commands.add(command);
    }

    /**
     * @param command
     * the command to remove
     */
    public void removeCommand(BRCommand command) {
        commands.remove(command);
    }

    /**
     * @param plugin
     * the plugin that registers the command.
     */
    public void register(JavaPlugin plugin) {
        plugin.getCommand(label).setExecutor(executor);
    }

}
