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
package de.erethon.commons.misc;

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
