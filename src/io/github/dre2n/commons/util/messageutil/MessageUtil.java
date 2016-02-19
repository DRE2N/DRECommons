package io.github.dre2n.commons.util.messageutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.javaplugin.BRPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MessageUtil {
	
	private static BRPlugin plugin = BRPlugin.getInstance();
	private static CompatibilityHandler compat = CompatibilityHandler.getInstance();
	
	private final static int CENTER_PX = 154;
	
	public static String center(String message) {
		if (message == null || message.equals("")) {
			return "";
		}
		
		message = ChatColor.translateAlternateColorCodes('&', message);
		
		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;
		
		for (char c : message.toCharArray()) {
			if (c == '§') {
				previousCode = true;
				continue;
			} else if (previousCode == true) {
				previousCode = false;
				if (c == 'l' || c == 'L') {
					isBold = true;
					continue;
				} else {
					isBold = false;
				}
			} else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
		}
		
		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while (compensated < toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}
		
		return sb.toString() + ChatColor.translateAlternateColorCodes('&', message);
	}
	
	/**
	 * Logs a message to the console.
	 * Supports color codes.
	 */
	public static void log(String message) {
		plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
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
		Bukkit.broadcastMessage(center(message));
	}
	
	/**
	 * Sends a message to a specific player (or another ConsoleSender).
	 * Supports color codes.
	 */
	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	/**
	 * Sends a perfectly centered message to a specific player (or another ConsoleSender).
	 * Supports color codes.
	 */
	public static void sendCenteredMessage(CommandSender sender, String message) {
		sender.sendMessage(center(message));
	}
	
	/**
	 * Sends the plugin name formatted to a player (or another sender), for example as a headline.
	 */
	public static void sendPluginTag(CommandSender sender, Plugin plugin) {
		sendCenteredMessage(sender, "&4&l[ &6" + plugin.getDescription().getName() + " &4&l]");
	}
	
	/**
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
	public static void sendScreenMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
		switch (compat.getInternals()) {
			case v1_9_R1:
				v1_9_R1.sendScreenMessage(player, title, subtitle, fadeIn, show, fadeOut);
				break;
			case v1_8_R3:
				v1_8_R3.sendScreenMessage(player, title, subtitle, fadeIn, show, fadeOut);
				break;
			case v1_8_R2:
				v1_8_R2.sendScreenMessage(player, title, subtitle, fadeIn, show, fadeOut);
				break;
			case v1_8_R1:
				v1_8_R1.sendScreenMessage(player, title, subtitle, fadeIn, show, fadeOut);
				break;
			case UNKNOWN:
				UNKNOWN.sendScreenMessage(player, title, subtitle, fadeIn, show, fadeOut);
				break;
			default:
				OUTDATED.sendScreenMessage(player, title, subtitle, fadeIn, show, fadeOut);
		}
	}
	
	/**
	 * @param player
	 * the player who will receive the message
	 * @param title
	 * the message of the first, big line
	 * @param subtitle
	 * the message of the second, small line
	 */
	public static void sendScreenMessage(Player player, String title, String subtitle) {
		sendScreenMessage(player, title, subtitle, 20, 60, 20);
	}
	
	/**
	 * @param player
	 * the player who will receive the message
	 * @param title
	 * the message of the first, big line
	 */
	public static void sendScreenMessage(Player player, String title) {
		sendScreenMessage(player, title, "", 20, 60, 20);
	}
	
}
