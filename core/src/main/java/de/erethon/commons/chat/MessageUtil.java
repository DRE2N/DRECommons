/*
 * Written from 2015-2018 by Daniel Saukel
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
package de.erethon.commons.chat;

import de.erethon.commons.compatibility.CompatibilityHandler;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class MessageUtil {

    static InternalsProvider internals;

    static {
        switch (CompatibilityHandler.getInstance().getInternals()) {
            /*case GLOWSTONE:
                internals = new Glowstone();*/
            case v1_13_R1:
                internals = new v1_13_R1();
                break;
            case v1_12_R1:
                internals = new v1_12_R1();
                break;
            case v1_11_R1:
                internals = new v1_11_R1();
                break;
            case v1_10_R1:
                internals = new v1_10_R1();
                break;
            case v1_9_R2:
                internals = new v1_9_R2();
                break;
            case v1_9_R1:
                internals = new v1_9_R1();
                break;
            case v1_8_R3:
                internals = new v1_8_R3();
                break;
            case v1_8_R2:
                internals = new v1_8_R2();
                break;
            case v1_8_R1:
                internals = new v1_8_R1();
                break;
            default:
                internals = new InternalsProvider(CompatibilityHandler.getInstance().isSpigot());
        }
    }

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
        String toSend = ChatColor.translateAlternateColorCodes('&', message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(toSend);
        }
    }

    /**
     * Broadcasts BaseComponents to all players.
     * Supports color codes.
     */
    public static void broadcastMessage(BaseComponent... message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(message);
        }
    }

    /**
     * Broadcasts a perfectly centered message to all players.
     * Supports color codes.
     */
    public static void broadcastCenteredMessage(String message) {
        String toSend = DefaultFontInfo.center(message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(toSend);
        }
    }

    /**
     * Broadcasts a perfectly centered BaseComponent message to all players.
     * Supports color codes.
     */
    public static void broadcastCenteredMessage(BaseComponent... message) {
        BaseComponent[] toSend = DefaultFontInfo.center(message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(toSend);
        }
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
        broadcastTitleMessage(title, subtitle, 20, 60, 20);
    }

    /**
     * Broadcasts a title message.
     * Supports color codes.
     *
     * @param title
     * the message of the first, big line
     */
    public static void broadcastTitleMessage(String title) {
        broadcastTitleMessage(title, new String(), 20, 60, 20);
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
     * Broadcasts a fat message
     * Does not support color codes.
     *
     * @param message
     * the message String
     */
    public static void broadcastFatMessage(ChatColor color, String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendFatMessage(player, color, message);
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
     * Sends a BaseComponent message to a specific player (or another CommandSender).
     * Supports color codes.
     */
    public static void sendMessage(CommandSender sender, BaseComponent... message) {
        if (sender instanceof Player) {
            ((Player) sender).spigot().sendMessage(message);
        } else {
            sender.sendMessage(TextComponent.toLegacyText(message));
        }
    }

    /**
     * Sends a perfectly centered message to a specific player (or another CommandSender).
     * Supports color codes.
     */
    public static void sendCenteredMessage(CommandSender sender, String message) {
        sender.sendMessage(DefaultFontInfo.center(message));
    }

    /**
     * Sends a perfectly centered BaseComponent message to a specific player (or another CommandSender).
     * Supports color codes.
     */
    public static void sendCenteredMessage(CommandSender sender, BaseComponent... message) {
        if (sender instanceof Player) {
            ((Player) sender).spigot().sendMessage(DefaultFontInfo.center(message));
        } else {
            sender.sendMessage(TextComponent.toLegacyText(message));
        }
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
        if (internals != null) {
            internals.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
        } else {
            MessageUtil.sendCenteredMessage(player, title);
            MessageUtil.sendCenteredMessage(player, subtitle);
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
        sendTitleMessage(player, title, new String(), 20, 60, 20);
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
        if (internals != null) {
            internals.sendActionBarMessage(player, message);
        } else {
            MessageUtil.sendCenteredMessage(player, message);
        }
    }

    /**
     * Sends a fat message.
     * Does not support color codes.
     *
     * @param player
     * the player who will receive the message
     * @param word
     * the word to send
     */
    public static void sendFatMessage(Player player, ChatColor color, String word) {
        word = ChatColor.translateAlternateColorCodes('&', word);
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);
        sendCenteredMessage(player, color + fat[0]);
        sendCenteredMessage(player, color + fat[1]);
        sendCenteredMessage(player, color + fat[2]);
        sendCenteredMessage(player, color + fat[3]);
        sendCenteredMessage(player, color + fat[4]);
    }

}
