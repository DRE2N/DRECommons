/*
 * Copyright (C) 2015-2016 Daniel Saukel
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
package io.github.dre2n.commons.util.messageutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class MessageUtil {

    protected static CompatibilityHandler compat = CompatibilityHandler.getInstance();

    /**
     * Logs a message to the console.
     * Supports color codes.
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Logs a message to the console.
     * Supports color codes.
     */
    public static void log(Plugin plugin, String message) {
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Broadcasts a message to all players.
     * Supports color codes.
     */
    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Broadcasts a perfectly centered message to all players.
     * Supports color codes.
     */
    public static void broadcastCenteredMessage(String message) {
        Bukkit.broadcastMessage(DefaultFontInfo.center(message));
    }

    /**
     * Broadcasts the plugin name formatted to a player (or another sender), for example as a headline.
     */
    public static void broadcastPluginTag(CommandSender sender, Plugin plugin) {
        broadcastCenteredMessage("&4&l[ &6" + plugin.getDescription().getName() + " &4&l]");
    }

    /**
     * Broadcasts a title message.
     * Supports color codes.
     *
     * @param title
     * the message of the first, big line
     * @param subtitle
     * the message of the second, small line
     * @param fadeIn
     * the time in ticks it takes for the message to appear
     * @param show
     * the time in ticks how long the message will be visible
     * @param fadeOut
     * the time in ticks it takes for the message to disappear
     */
    public static void broadcastTitleMessage(String title, String subtitle, int fadeIn, int show, int fadeOut) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
        }
    }

    /**
     * Broadcasts a title message.
     * Supports color codes.
     *
     * @param title
     * the message of the first, big line
     * @param subtitle
     * the message of the second, small line
     */
    public static void broadcastTitleMessage(String title, String subtitle) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitleMessage(player, title, subtitle, 20, 60, 20);
        }
    }

    /**
     * Broadcasts a title message.
     * Supports color codes.
     *
     * @param title
     * the message of the first, big line
     */
    public static void broadcastTitleMessage(String title) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitleMessage(player, title, "", 20, 60, 20);
        }
    }

    /**
     * Broadcasts an action bar message.
     * Supports color code.
     *
     * @param message
     * the message String
     */
    public static void broadcastActionBarMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendActionBarMessage(player, message);
        }
    }

    /**
     * Sends an item bar message.
     * Supports color codes.
     *
     * WARNING: Very hacky. Fails in Spectator Mode!
     *
     * @param message
     * the message String
     */
    public static void broadcastItemBarMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendItemBarMessage(player, message);
        }
    }

    /**
     * Sends a message to a specific player (or another CommandSender).
     * Supports color codes.
     */
    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Sends a perfectly centered message to a specific player (or another CommandSender).
     * Supports color codes.
     */
    public static void sendCenteredMessage(CommandSender sender, String message) {
        sender.sendMessage(DefaultFontInfo.center(message));
    }

    /**
     * Sends the plugin name formatted to a player (or another sender), for example as a headline.
     */
    public static void sendPluginTag(CommandSender sender, Plugin plugin) {
        sendCenteredMessage(sender, "&4&l[ &6" + plugin.getDescription().getName() + " &4&l]");
    }

    /**
     * Sends a title message.
     * Supports color codes.
     *
     * @param player
     * the player who will receive the message
     * @param title
     * the message of the first, big line
     * @param subtitle
     * the message of the second, small line
     * @param fadeIn
     * the time in ticks it takes for the message to appear
     * @param show
     * the time in ticks how long the message will be visible
     * @param fadeOut
     * the time in ticks it takes for the message to disappear
     */
    public static void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        switch (compat.getInternals()) {
            case v1_9_R1:
                v1_9_R1.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
                break;
            case v1_8_R3:
                v1_8_R3.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
                break;
            case v1_8_R2:
                v1_8_R2.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
                break;
            case v1_8_R1:
                v1_8_R1.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
                break;
            case UNKNOWN:
                UNKNOWN.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
                break;
            default:
                MessageUtil.sendCenteredMessage(player, title);
                MessageUtil.sendCenteredMessage(player, title);
        }
    }

    /**
     * Sends a title message.
     * Supports color codes.
     *
     * @param player
     * the player who will receive the message
     * @param title
     * the message of the first, big line
     * @param subtitle
     * the message of the second, small line
     */
    public static void sendTitleMessage(Player player, String title, String subtitle) {
        sendTitleMessage(player, title, subtitle, 20, 60, 20);
    }

    /**
     * Sends a title message.
     * Supports color codes.
     *
     * @param player
     * the player who will receive the message
     * @param title
     * the message of the first, big line
     */
    public static void sendTitleMessage(Player player, String title) {
        sendTitleMessage(player, title, "", 20, 60, 20);
    }

    /**
     * Sends an action bar message.
     * Supports color code.
     *
     * @param player
     * the player who will receive the message
     * @param message
     * the message String
     */
    public static void sendActionBarMessage(Player player, String message) {
        switch (compat.getInternals()) {
            case v1_9_R1:
                v1_9_R1.sendActionBarMessage(player, message);
                break;
            case v1_8_R3:
                v1_8_R3.sendActionBarMessage(player, message);
                break;
            case v1_8_R2:
                v1_8_R2.sendActionBarMessage(player, message);
                break;
            case v1_8_R1:
                v1_8_R1.sendActionBarMessage(player, message);
                break;
            default:
                MessageUtil.sendCenteredMessage(player, message);
                MessageUtil.sendCenteredMessage(player, message);
        }
    }

    /**
     * Sends an item bar message.
     * Supports color codes.
     *
     * WARNING: Very hacky. Fails in Spectator Mode!
     *
     * @param player
     * the player who will receive the message
     * @param message
     * the message String
     */
    public static void sendItemBarMessage(Player player, String message) {
        switch (compat.getInternals()) {
            case v1_9_R1:
                v1_9_R1.sendItemBarMessage(player, message);
                break;
            case v1_8_R3:
                v1_8_R3.sendItemBarMessage(player, message);
                break;
            case v1_8_R2:
                v1_8_R2.sendItemBarMessage(player, message);
                break;
            case v1_8_R1:
                v1_8_R1.sendItemBarMessage(player, message);
                break;
            default:
                MessageUtil.sendCenteredMessage(player, message);
                MessageUtil.sendCenteredMessage(player, message);
        }
    }

}
