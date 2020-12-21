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
package de.erethon.commons.chat;

import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Internals;
import de.erethon.commons.javaplugin.DREPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Daniel Saukel
 */
public class MessageUtil {

    private static boolean is1_9 = Internals.isAtLeast(Internals.v1_9_R1);
    private static boolean is1_11 = Internals.isAtLeast(Internals.v1_11_R1);
    private static boolean is1_16 = Internals.isAtLeast(Internals.v1_16_R1);

    static InternalsProvider internals;

    static {
        if (!is1_11) {
            String packageName = MessageUtil.class.getPackage().getName();
            String internalsName = CompatibilityHandler.getInstance().getInternals().toString();
            try {
                internals = (InternalsProvider) Class.forName(packageName + "." + internalsName).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
                MessageUtil.log(ChatColor.DARK_RED + "MessageUtil could not find a valid implementation for " + internalsName + ".");
            }
        }
    }

    /**
     * Logs a message to the console. Supports color codes.
     *
     * @param message the message String
     */
    public static void log(String message) {
        log(DREPlugin.getInstance(), message);
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
        broadcastMessage(parse(message));
    }

    /**
     * Broadcasts BaseComponents to all players.
     *
     * @param message the message components
     */
    public static void broadcastMessage(BaseComponent... message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendMessage(p, message));
    }

    /**
     * Broadcasts a perfectly centered message to all players. Supports color codes.
     *
     * @param message the message String
     */
    public static void broadcastCenteredMessage(String message) {
        broadcastCenteredMessage(parse(message));
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
        broadcastActionBarMessage(message);
    }

    /**
     * Broadcasts an action bar message.
     *
     * @param message the message components
     */
    public static void broadcastActionBarMessage(BaseComponent[] message) {
        Bukkit.getOnlinePlayers().forEach(p -> sendActionBarMessage(p, message));
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
        sendMessage(sender, parse(message));
    }

    /**
     * Sends a BaseComponent message to a specific player (or another CommandSender).
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendMessage(CommandSender sender, BaseComponent... message) {
        if (sender instanceof Player) {
            ((Player) sender).spigot().sendMessage(message);
        } else {
            sender.sendMessage(BaseComponent.toPlainText(message));
        }
    }

    /**
     * Sends a perfectly centered message to a specific player (or another CommandSender). Supports color codes.
     *
     * @param sender  the sender
     * @param message the message String
     */
    public static void sendCenteredMessage(CommandSender sender, String message) {
        sendCenteredMessage(sender, parse(message));
    }

    /**
     * Sends a perfectly centered BaseComponent message to a specific player (or another CommandSender).
     *
     * @param sender  the sender
     * @param message the message components
     */
    public static void sendCenteredMessage(CommandSender sender, BaseComponent... message) {
        sendMessage(sender, DefaultFontInfo.center(message));
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
        title = TextComponent.toLegacyText(parse(title));
        subtitle = TextComponent.toLegacyText(parse(subtitle));
        if (is1_11) {
            player.sendTitle(title, subtitle, fadeIn, show, fadeOut);
        } else {
            internals.sendTitle(player, title, subtitle, fadeIn, show, fadeOut);
        }
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
        sendActionBarMessage(player, parse(message));
    }

    /**
     * Sends an action bar message.
     *
     * @param player  the player who will receive the message
     * @param message the message components
     */
    public static void sendActionBarMessage(Player player, BaseComponent[] message) {
        if (is1_9) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
        } else {
            internals.sendActionBar(player, ComponentSerializer.toString(message));
        }
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

    /**
     * Parses the string.
     * <p>
     * Translates color codes and in 1.16+ MiniMessage tags.
     *
     * @param string the String to parse
     * @return the parsed BaseComponents
     */
    public static BaseComponent[] parse(String string) {
        string = ChatColor.translateAlternateColorCodes('&', string);
        // This is necessary until we do a full update to adventure-text
        return is1_16 ? BungeeComponentSerializer.get().serialize(MiniMessage.get().parse(string)) : TextComponent.fromLegacyText(string);

    }

}
