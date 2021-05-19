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
package de.erethon.commons.config;

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.javaplugin.DREPlugin;

/**
 * @author Daniel Saukel
 */
public interface Message {

    /**
     * Returns the configuration path where the message is loaded from.
     *
     * @return the configuration path where the message is loaded from
     */
    String getPath();

    /**
     * The MessageHandler loaded by the plugin.
     *
     * @return the MessageHandler loaded by the plugin.
     */
    default MessageHandler getMessageHandler() {
        return DREPlugin.getInstance().getMessageHandler();
    }

    /**
     * Returns the formatted message String.
     *
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    default String getMessage() {
        return getMessageHandler().getMessage(this);
    }

    /**
     * Returns the formatted message String.
     *
     * @param args Strings to replace possible variables in the message
     * @return the formatted message String;
     *         null, if the path is null;
     *         a placeholder, if the configuration is erroneous.
     */
    default String getMessage(String... args) {
        return getMessageHandler().getMessage(this, args);
    }

    /**
     * Sends the message to the console.
     */
    default void debug() {
        MessageUtil.log(DREPlugin.getInstance(), getMessage());
    }

}
