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

/**
 * An enum that implements Message needs this static methods, too:
 public static Message getByIdentifier(String identifier) - returns the message that matchs the config identifier
 public static FileConfiguration toConfig() - lists all values in a FileConfiguration
 *
 * @author Daniel Saukel
 */
public interface Message {

    /**
     * @return
     * the identifier
     */
    public String getIdentifier();

    /**
     * @return the message
     */
    public String getMessage();

    /**
     * @param args
     * String to replace possible variables in the message
     */
    public String getMessage(String... args);

    /**
     * @param message
     * the message to set
     */
    public void setMessage(String message);

}
