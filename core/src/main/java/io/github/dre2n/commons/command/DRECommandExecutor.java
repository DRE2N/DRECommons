/*
 * Copyright (C) 2012-2017 Frank Baumann
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
import io.github.dre2n.commons.javaplugin.DREPlugin;
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
                        MessageUtil.log("&cThis command may not be executed by the console!");
                        return false;
                    }
                }

                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (!command.isPlayerCommand()) {
                        MessageUtil.sendMessage(player, "&cThis command may not be executed by a player!");
                        return false;

                    } else if (command.getPermission() != null) {
                        if (!command.playerHasPermissions(player)) {
                            MessageUtil.sendMessage(player, "&cYou do not have permission to use this command!");
                            return false;
                        }
                    }
                }

                if (command.getMinArgs() <= args.length - 1 & command.getMaxArgs() >= args.length - 1 || command.getMinArgs() == -1) {
                    command.onExecute(args, sender);
                    return true;

                } else {
                    command.displayHelp(sender);
                    return true;
                }
            }
        }

        command = plugin.getCommandCache().getCommand("main");
        if (command != null) {
            command.onExecute(null, sender);

        } else {
            MessageUtil.sendMessage(sender, "&cThis command does not exist!");
        }

        return true;
    }

}
