/*
 * Written from 2015-2020 by Daniel Saukel
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
import de.erethon.commons.config.CommonMessage;
import de.erethon.commons.javaplugin.DREPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * The default CommandExecutor for all DRECommandCache.
 *
 * @author Frank Baumann, Daniel Saukel
 */
public class DRECommandExecutor implements CommandExecutor {

    protected DREPlugin plugin;

    public DRECommandExecutor(DREPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command unused1, String unused2, String[] args) {
        DRECommand command;

        if (args.length > 0) {
            command = plugin.getCommandCache().getCommand(args[0]);

            if (command != null) {
                if (sender instanceof ConsoleCommandSender) {
                    if (!command.isConsoleCommand()) {
                        MessageUtil.log(CommonMessage.CMD_NO_CONSOLE_COMMAND.getMessage());
                        return false;
                    }

                } else if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (!command.isPlayerCommand()) {
                        MessageUtil.sendMessage(player, CommonMessage.CMD_NO_PLAYER_COMMAND.getMessage());
                        return false;

                    } else if (command.getPermission() != null) {
                        if (!command.senderHasPermissions(player)) {
                            MessageUtil.sendMessage(player, CommonMessage.CMD_NO_PERMISSION.getMessage());
                            return false;
                        }
                    }
                }

                if (command.getMinArgs() <= args.length - 1 & command.getMaxArgs() >= args.length - 1 || command.getMinArgs() == -1) {
                    command.onExecute(args, sender);

                } else {
                    command.displayHelp(sender);
                }
                return true;
            }
        }

        command = plugin.getCommandCache().getCommand("main");
        if (command != null) {
            command.onExecute(args, sender);

        } else {
            MessageUtil.sendMessage(sender, CommonMessage.CMD_DOES_NOT_EXIST.getMessage());
        }

        return true;
    }

}
