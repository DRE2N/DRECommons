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

import de.erethon.commons.chat.MessageUtil;
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
     * @param player the player to check
     * @return if the player has permission to use the command
     */
    public boolean playerHasPermissions(Player player) {
        if (player.hasPermission(permission) || permission == null) {
            return true;
        }

        return false;
    }

    /**
     * @return the command name
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command name to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the command aliases
     */
    public Set<String> getAliases() {
        return aliases;
    }

    /**
     * @param aliases the command aliases to set
     */
    public void setAliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }

    /**
     * @return the minimal amount of arguments
     */
    public int getMinArgs() {
        return minArgs;
    }

    /**
     * @param minArgs the minimal amount of arguments to set
     */
    public void setMinArgs(int minArgs) {
        this.minArgs = minArgs;
    }

    /**
     * @return the maximum amount of arguments
     */
    public int getMaxArgs() {
        return maxArgs;
    }

    /**
     * @param maxArgs the maximum amount of arguments to set
     */
    public void setMaxArgs(int maxArgs) {
        this.maxArgs = maxArgs;
    }

    /**
     * @return the help message
     */
    public String getHelp() {
        return help;
    }

    /**
     * @param help the help message to set
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * @return the permission to use the command
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission the permission to use the command to set
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return if a player may use the command
     */
    public boolean isPlayerCommand() {
        return playerCommand;
    }

    /**
     * @param playerCommand set if a player may use the command
     */
    public void setPlayerCommand(boolean playerCommand) {
        this.playerCommand = playerCommand;
    }

    /**
     * @return if the console may use the command
     */
    public boolean isConsoleCommand() {
        return consoleCommand;
    }

    /**
     * @param consoleCommand set if the console may use the command
     */
    public void setConsoleCommand(boolean consoleCommand) {
        this.consoleCommand = consoleCommand;
    }

    /* Abstracts */
    /**
     * @param args   the arguments to pass from the command
     * @param sender the player or console that sent the command
     */
    public abstract void onExecute(String[] args, CommandSender sender);

}
