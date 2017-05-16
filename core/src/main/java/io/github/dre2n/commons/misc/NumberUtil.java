/*
 * Copyright (C) 2015-2017 Daniel Saukel
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
package io.github.dre2n.commons.misc;

import java.util.Random;

/**
 * @author Daniel Saukel
 */
public class NumberUtil {

    /* Integer */
    /**
     * @param string
     * the String to parse
     * @return
     * the number as an int
     */
    public static int parseInt(String string) {
        int i;
        try {
            i = Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            i = 0;
        }

        return i;
    }

    /**
     * @param string
     * the String to parse
     * @param defaultReturn
     * the value which will be returned if the String is not parsable
     * @return
     * the number as an int
     */
    public static int parseInt(String string, int defaultReturn) {
        int i;
        try {
            i = Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            i = defaultReturn;
        }

        return i;
    }

    /**
     * @param min
     * the lowest possible return
     * @param max
     * the highest possible return
     * @return
     * a random number between min and max
     */
    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) + min;
    }

    /* Double */
    /**
     * @param string
     * the String to parse
     * @return
     * the number as a double
     */
    public static double parseDouble(String string) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            d = 0;
        }

        return d;
    }

    /**
     * @param string
     * the String to parse
     * @param defaultReturn
     * the value which will be returned if the String is not parsable
     * @return
     * the number as a double
     */
    public static double parseDouble(String string, double defaultReturn) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            d = defaultReturn;
        }

        return d;
    }

}
