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

import de.erethon.commons.chat.MessageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.erethon.commons.config.CommonMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel, Fyreum
 */
public abstract class DRECommand {

    private String command;
    private final Set<String> aliases = new HashSet<>();
    private int minArgs;
    private int maxArgs;
    private String help;
    private String permission;
    private boolean playerCommand;
    private boolean consoleCommand;
    private final CommandCache subCommands = new CommandCache();

    public void displayHelp(CommandSender sender) {
        MessageUtil.sendMessage(sender, ChatColor.RED + help);
    }

    public void addSubCommand(DRECommand command) {
        subCommands.addCommand(command);
    }

    public void addSubCommands(DRECommand... commands) {
        subCommands.addCommands(commands);
    }

    /**
     * @param arg the arg to check
     *
     * @return true if the name or one alias matches the arg
     */
    public boolean matches(String arg) {
        return arg.equalsIgnoreCase(command) | aliases.contains(arg);
    }

    /**
     * @param sender the sender to check
     *
     * @return if the sender has permission to use the command
     */
    public boolean senderHasPermissions(CommandSender sender) {
        return permission == null || permission.isEmpty() || sender.hasPermission(permission);
    }

    /**
     * Returns a list of strings to tab complete including all sub commands of this class.
     *
     * @param sender the command sender
     * @param args the given args
     *
     * @return a list of strings to tab complete including all sub commands
     */
    public final List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> completes = new ArrayList<>();
        List<String> commandCompletes = onTabComplete(sender, args);
        String cmd = args[1];

        if (commandCompletes != null) {
            completes.addAll(commandCompletes);
        }

        if(args.length == 2) {
            List<String> cmds = new ArrayList<>();
            for (DRECommand command : subCommands.getCommands()) {
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
        for (DRECommand command : subCommands.getCommands()) {
            if (command.matches(cmd)) {
                completes.addAll(command.tabComplete(sender, Arrays.copyOfRange(args, 1, args.length)));
            }
        }
        return completes;
    }

    /**
     * Returns a list of strings to tab complete.
     * The returned value can be null.
     * This method will most likely be overridden by the certain command.
     *
     * @param sender the command sender
     * @param args the given args
     *
     * @return a list of strings to tab complete
     */
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

    public final void execute(String[] args, CommandSender sender) {
        if (args.length != 0) {
            String[] argsCopy = Arrays.copyOfRange(args, 1, args.length);

            if (argsCopy.length > 0) {
                DRECommand command = this.subCommands.getCommand(argsCopy[0]);

                if (command != null) {
                    if (sender instanceof ConsoleCommandSender) {
                        if (!command.isConsoleCommand()) {
                            MessageUtil.log(CommonMessage.CMD_NO_CONSOLE_COMMAND.getMessage());
                            return;
                        }
                    } else if (sender instanceof Player) {
                        Player player = (Player)sender;
                        if (!command.isPlayerCommand()) {
                            MessageUtil.sendMessage(player, CommonMessage.CMD_NO_PLAYER_COMMAND.getMessage());
                            return;
                        }

                        if (!command.senderHasPermissions(player)) {
                            MessageUtil.sendMessage(player, CommonMessage.CMD_NO_PERMISSION.getMessage());
                            return;
                        }
                    }

                    if (!(command.getMinArgs() <= argsCopy.length - 1 & command.getMaxArgs() >= argsCopy.length - 1) && command.getMinArgs() != -1) {
                        command.displayHelp(sender);
                        return;
                    }

                    command.execute(argsCopy, sender);
                    return;
                }
            }
        }
        onExecute(args, sender);
    }

    /* getter and setter */

    protected String getFinalArg(String[] args, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            sb.append(args[i]);
            if (i != args.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
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

    /**
     * @return the sub command cache
     */
    public CommandCache getSubCommands() {
        return subCommands;
    }

    /* Abstracts */
    /**
     * @param args   the arguments to pass from the command
     * @param sender the player or console that sent the command
     */
    public abstract void onExecute(String[] args, CommandSender sender);
}
