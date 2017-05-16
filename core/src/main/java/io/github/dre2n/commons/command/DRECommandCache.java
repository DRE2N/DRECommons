/*
 * Copyright (C)2015-2017 Daniel Saukel
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

import io.github.dre2n.commons.javaplugin.DREPlugin;
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
     * @param command
     * usually the first command argument
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
     * @param command
     * the command to add
     */
    public void addCommand(DRECommand command) {
        commands.add(command);
    }

    /**
     * @param command
     * the command to remove
     */
    public void removeCommand(DRECommand command) {
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
