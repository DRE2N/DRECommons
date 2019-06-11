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
package de.erethon.commons.chat;

import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.player.PlayerUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
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
        String packageName = MessageUtil.class.getPackage().getName();
        String internalsName = CompatibilityHandler.getInstance().getInternals().toString();
        try {
            internals = (InternalsProvider) Class.forName(packageName + "." + internalsName).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
            internals = new InternalsProvider();
            MessageUtil.log(ChatColor.DARK_RED + "MessageUtil could not find a valid implementation for " + internalsName + ".");
        }
    }

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param message the message String
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param plugin  the logging plugin
     * @param message the message String
     */
    public static void log(Plugin plugin, String message) {
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Broadcasts a message to all players. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastMessage(String message) {
        String toSend = ChatColor.translateAlternateColorCodes('&', message);
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(toSend));
    }

    /**
     * Broadcasts BaseComponents to all players.
     *
     * @param message the message components
     */
    public static void broadcastMessage(BaseComponent... message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendMessage(p, ChatMessageType.CHAT, message));
    }

    /**
     * Broadcasts a perfectly centered message to all players. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastCenteredMessage(String message) {
        broadcastMessage(DefaultFontInfo.center(message));
    }

    /**
     * Broadcasts a perfectly centered BaseComponent message to all players.
     *
     * @param message the message components
     */
    public static void broadcastCenteredMessage(BaseComponent... message) {
        broadcastMessage(DefaultFontInfo.center(message));
    }

    /**
     * Broadcasts the plugin name formatted to a player (or another sender), for example as a headline.
     *
     * @param sender the sender
     * @param plugin the plugin
     */
    public static void broadcastPluginTag(CommandSender sender, Plugin plugin) {
        broadcastCenteredMessage("&4&l[ &6" + plugin.getDescription().getName() + " &4&l]");
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     * @param fadeIn   the time in ticks it takes for the message to appear
     * @param show     the time in ticks how long the message will be visible
     * @param fadeOut  the time in ticks it takes for the message to disappear
     */
    public static void broadcastTitleMessage(String title, String subtitle, int fadeIn, int show, int fadeOut) {
        Bukkit.getOnlinePlayers().forEach(p -> sendTitleMessage(p, title, subtitle, fadeIn, show, fadeOut));
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     */
    public static void broadcastTitleMessage(String title, String subtitle) {
        broadcastTitleMessage(title, subtitle, 20, 60, 20);
    }

    /**
     * Broadcasts a title message. Supports color codes.
     *
     * @param title the message of the first, big line
     */
    public static void broadcastTitleMessage(String title) {
        broadcastTitleMessage(title, "", 20, 60, 20);
    }

    /**
     * Broadcasts an action bar message. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastActionBarMessage(String message) {
        BaseComponent[] comps = new BaseComponent[]{new TextComponent(ChatColor.translateAlternateColorCodes('&', message))};
        Bukkit.getOnlinePlayers().forEach(p -> sendMessage(p, ChatMessageType.ACTION_BAR, comps));
    }

    /**
     * Broadcasts an action bar message.
     *
     * @param message the message components
     */
    public static void broadcastActionBarMessage(BaseComponent[] message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendMessage(p, ChatMessageType.ACTION_BAR, message));
    }

    /**
     * Broadcasts a fat message Does not support color codes.
     *
     * @param color the color of the message
     * @param word  the word to send
     */
    public static void broadcastFatMessage(ChatColor color, String word) {
        word = ChatColor.translateAlternateColorCodes('&', word);
        word = ChatColor.stripColor(word);
        String[] fat = FatLetter.fromString(word);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendCenteredMessage(player, color + fat[0]);
            sendCenteredMessage(player, color + fat[1]);
            sendCenteredMessage(player, color + fat[2]);
            sendCenteredMessage(player, color + fat[3]);
            sendCenteredMessage(player, color + fat[4]);
        }
    }

    /**
     * Sends a message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Sends a BaseComponent message to a specific player (or another CommandSender).
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendMessage(CommandSender sender, BaseComponent... message) {
        sendMessage(sender, ChatMessageType.CHAT, message);
    }

    /**
     * Sends a perfectly centered message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendCenteredMessage(CommandSender sender, String message) {
        sender.sendMessage(DefaultFontInfo.center(message));
    }

    /**
     * Sends a perfectly centered BaseComponent message to a specific player (or another CommandSender).
     *
     * @param sender  the sender
     * @param message the message components
     */
    public static void sendCenteredMessage(CommandSender sender, BaseComponent... message) {
        sendMessage(sender, ChatMessageType.CHAT, DefaultFontInfo.center(message));
    }

    /**
     * Sends the plugin name formatted to a player (or another sender), for example as a headline.
     *
     * @param sender the sender
     * @param plugin the plugin
     */
    public static void sendPluginTag(CommandSender sender, Plugin plugin) {
        sendCenteredMessage(sender, "&4&l[ &6" + plugin.getDescription().getName() + " &4&l]");
    }

    /**
     * Sends a title message. Supports color codes.
     *
     * @param player   the player who will receive the message
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     * @param fadeIn   the time in ticks it takes for the message to appear
     * @param show     the time in ticks how long the message will be visible
     * @param fadeOut  the time in ticks it takes for the message to disappear
     */
    public static void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        title = ChatColor.translateAlternateColorCodes('&', title);
        internals.sendTitleMessage(player, title, subtitle, fadeIn, show, fadeOut);
    }

    /**
     * Sends a title message. Supports color codes.
     *
     * @param player   the player who will receive the message
     * @param title    the message of the first, big line
     * @param subtitle the message of the second, small line
     */
    public static void sendTitleMessage(Player player, String title, String subtitle) {
        sendTitleMessage(player, title, subtitle, 20, 60, 20);
    }

    /**
     * Sends a title message. Supports color codes.
     *
     * @param player the player who will receive the message
     * @param title  the message of the first, big line
     */
    public static void sendTitleMessage(Player player, String title) {
        sendTitleMessage(player, title, "", 20, 60, 20);
    }

    /**
     * Sends an action bar message. Supports color codes.
     *
     * @param player  the player who will receive the message
     * @param message the message String
     */
    public static void sendActionBarMessage(Player player, String message) {
        BaseComponent[] comps = new BaseComponent[]{new TextComponent(ChatColor.translateAlternateColorCodes('&', message))};
        sendMessage(player, ChatMessageType.ACTION_BAR, comps);
    }

    /**
     * Sends an action bar message.
     *
     * @param player  the player who will receive the message
     * @param message the message components
     */
    public static void sendActionBarMessage(Player player, BaseComponent[] message) {
        sendMessage(player, ChatMessageType.ACTION_BAR, message);
    }

    /**
     * Sends a fat message. Does not support color codes.
     *
     * @param player the player who will receive the message
     * @param color  the color of the message
     * @param word   the word to send
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

    private static void sendMessage(CommandSender sender, ChatMessageType type, BaseComponent[] message) {
        if (sender instanceof Player) {
            PlayerUtil.sendPacket((Player) sender, internals.buildPacketPlayOutChat(type, ComponentSerializer.toString(message)));
        } else {
            sender.sendMessage(TextComponent.toLegacyText(message));
        }
    }

}
