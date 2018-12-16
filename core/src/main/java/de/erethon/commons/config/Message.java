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
package de.erethon.commons.config;

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.javaplugin.DREPlugin;
import org.bukkit.ChatColor;

/**
 * An enum that implements Message needs this static methods, too:
 * public static Message getByIdentifier(String identifier) - returns the message that matchs the config identifier
 * public static FileConfiguration toConfig() - lists all values in a FileConfiguration
 *
 * @author Daniel Saukel
 */
public interface Message {

    /**
     * @return
     * the identifier
     */
    String getIdentifier();

    /**
     * @return
     * the unformatted message
     */
    String getRaw();

    /**
     * @return
     * the formatted message
     */
    default String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', getRaw());
    }

    /**
     * @param args
     * String to replace possible variables in the message
     */
    default String getMessage(String... args) {
        return getMessage(this, args);
    }

    /**
     * @param message
     * the message to set
     */
    void setMessage(String message);

    /* Actions */
    /**
     * Sends the message to the console.
     */
    default void debug() {
        MessageUtil.log(DREPlugin.getInstance(), getMessage());
    }

    /* Statics */
    /**
     * @param message
     * the matching enum value from the Message implementation
     * @param args
     * the args to replace &v[i]
     * @return
     * the message String to send
     */
    static String getMessage(Message message, String... args) {
        String output = message.getMessage();

        int i = 0;
        for (String arg : args) {
            i++;

            if (arg != null) {
                output = output.replace("&v" + i, arg);

            } else {
                output = output.replace("&v" + i, "null");
            }
        }

        return output;
    }

}
