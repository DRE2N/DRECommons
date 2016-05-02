/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.commons.config;

/**
 * An enum that implements Messages needs this static methods, too:
 * public static Messages getByIdentifier(String identifier) - returns the message that matchs the config identifier
 * public static FileConfiguration toConfig() - lists all values in a FileConfiguration
 *
 * @author Daniel Saukel
 */
public interface Messages {

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
