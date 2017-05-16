/*
 * Copyright (C) 2015-2017 Daniel Saukel
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

import io.github.dre2n.commons.chat.MessageUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public abstract class DRECommand {

    private String command;
    private Set<String> aliases = new HashSet<>();
    private int minArgs;
    private int maxArgs;
    private String help;
    private String permission;
    private boolean playerCommand;
    private boolean consoleCommand;

    public void displayHelp(CommandSender sender) {
        MessageUtil.sendMessage(sender, ChatColor.RED + help);
    }

    /**
     * @param player
     * the player to check
     * @return
     * if the player has permission to use the command
     */
    public boolean playerHasPermissions(Player player) {
        if (player.hasPermission(permission) || permission == null) {
            return true;
        }

        return false;
    }

    /**
     * @return
     * the command name
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command
     * the command name to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return
     * the command aliases
     */
    public Set<String> getAliases() {
        return aliases;
    }

    /**
     * @param aliases
     * the command aliases to set
     */
    public void setAliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }

    /**
     * @return
     * the minimal amount of arguments
     */
    public int getMinArgs() {
        return minArgs;
    }

    /**
     * @param minArgs
     * the minimal amount of arguments to set
     */
    public void setMinArgs(int minArgs) {
        this.minArgs = minArgs;
    }

    /**
     * @return
     * the maximum amount of arguments
     */
    public int getMaxArgs() {
        return maxArgs;
    }

    /**
     * @param maxArgs
     * the maximum amount of arguments to set
     */
    public void setMaxArgs(int maxArgs) {
        this.maxArgs = maxArgs;
    }

    /**
     * @return
     * the help message
     */
    public String getHelp() {
        return help;
    }

    /**
     * @param help
     * the help message to set
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * @return
     * the permission to use the command
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     * the permission to use the command to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return
     * if a player may use the command
     */
    public boolean isPlayerCommand() {
        return playerCommand;
    }

    /**
     * @param playerCommand
     * set if a player may use the command
     */
    public void setPlayerCommand(boolean playerCommand) {
        this.playerCommand = playerCommand;
    }

    /**
     * @return
     * if the console may use the command
     */
    public boolean isConsoleCommand() {
        return consoleCommand;
    }

    /**
     * @param consoleCommand
     * set if the console may use the command
     */
    public void setConsoleCommand(boolean consoleCommand) {
        this.consoleCommand = consoleCommand;
    }

    /* Abstracts */
    /**
     * @param args
     * the arguments to pass from the command
     * @param sender
     * the player or console that sent the command
     */
    public abstract void onExecute(String[] args, CommandSender sender);

}
