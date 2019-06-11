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

import java.lang.reflect.Field;

/**
 * @author Daniel Saukel
 */
public class FatLetter {

    public static final String[] A = {
        "IIIIIIIIIIII  ",
        " IIII   IIII  ",
        " IIIIIIIIIII  ",
        " IIII   IIII  ",
        "IIIIII IIIIII "
    };

    public static final String[] B = {
        "IIIIIIIIIII  ",
        " IIII   IIII ",
        " IIIIIIIIIII ",
        " IIII   IIII ",
        "IIIIIIIIIII  "
    };

    public static final String[] C = {
        "  IIIIIIIII  ",
        " IIII   IIII ",
        " III           ",
        " IIII   IIII ",
        "  IIIIIIIII  "
    };

    public static final String[] D = {
        "IIIIIIIIIII  ",
        " IIII   IIII ",
        " IIII   IIII ",
        " IIII   IIII ",
        "IIIIIIIIIII  "
    };

    public static final String[] E = {
        "IIIIIIIII ",
        " IIII   I ",
        " IIIIII   ",
        " IIII   I ",
        "IIIIIIIII "
    };

    public static final String[] F = {
        " IIIIIIII ",
        " IIII     ",
        " IIIIII   ",
        " IIII     ",
        " IIII     "
    };

    public static final String[] G = {
        "  IIIIIIII  ",
        " IIII       ",
        " III  IIIII ",
        " IIII   III ",
        "  IIIIIII I  "
    };

    public static final String[] H = {
        "IIIIII IIIIII ",
        " IIII   IIII  ",
        " IIIIIIIIIII  ",
        " IIII   IIII  ",
        "IIIIII IIIIII "
    };

    public static final String[] I = {
        "IIIIII ",
        " IIII  ",
        " IIII  ",
        " IIII  ",
        "IIIIII "
    };

    public static final String[] J = {
        "   IIIIII ",
        "    IIII  ",
        "    IIII  ",
        "I  IIIII  ",
        " IIIIII   "
    };

    public static final String[] K = {
        "IIIIII IIIII ",
        " IIII IIII   ",
        " IIIIIIII    ",
        " IIII IIII   ",
        "IIIIII IIIII "
    };

    public static final String[] L = {
        "IIII      ",
        "IIII      ",
        "IIII      ",
        "IIIIIIIII ",
        "IIIIIIIII "
    };

    public static final String[] M = {
        "IIIIII    IIIIII ",
        " IIIIIIIIIIIIII  ",
        " IIII IIII IIII  ",
        " IIII  II  IIII  ",
        "IIIIII    IIIIII "
    };

    public static final String[] N = {
        "IIIIIIII IIIIII ",
        " IIIIIIII IIII  ",
        " IIII IIIIIIII  ",
        " IIII  IIIIIII  ",
        "IIIIII  IIIIIII "
    };

    public static final String[] O = {
        " IIIIIIII ",
        "IIII  IIII ",
        "III    III ",
        "IIII  IIII ",
        " IIIIIIII  "
    };

    public static final String[] P = {
        "IIIIIIIII  ",
        "IIII  IIII ",
        "IIIIIIIII  ",
        "IIII       ",
        "IIII       "
    };

    public static final String[] Q = {
        " IIIIIIII ",
        "IIII  IIII ",
        "III  I III ",
        "IIII  IIII ",
        " IIIIIIII I "
    };

    public static final String[] R = {
        "IIIIIIIII  ",
        "IIII  IIII ",
        "IIIIIIIII  ",
        "IIII IIII  ",
        "IIII  IIII "
    };

    public static final String[] S = {
        " IIIIII ",
        "IIII  I ",
        " IIIII  ",
        "I  IIII ",
        "IIIIII  "
    };

    public static final String[] T = {
        "IIIIIIIIII ",
        "I  IIII  I ",
        "   IIII    ",
        "   IIII    ",
        "   IIII    "
    };

    public static final String[] U = {
        "IIIII IIIII ",
        " IIII  IIII ",
        " IIII  IIII ",
        " IIII  IIII ",
        "  IIIIIIII  "
    };

    public static final String[] V = {
        "IIIII IIIII ",
        " IIII  IIII ",
        " IIII  IIII ",
        "   IIIIII   ",
        "    IIII    "
    };

    public static final String[] W = {
        "IIIII IIIII IIIII ",
        " IIII  IIII  IIII ",
        " IIII  IIII  IIII ",
        "   IIIIIIIIIIII   ",
        "    IIII  IIII    "
    };

    public static final String[] X = {
        "IIIII   IIIII ",
        "  IIII IIII   ",
        "   IIIIIII    ",
        "  IIII IIII   ",
        "IIIII   IIIII "
    };

    public static final String[] Y = {
        "IIIII    IIIII ",
        "  IIII  IIII   ",
        "   IIIIIIII    ",
        "     IIII      ",
        "     IIII      ",
        "    IIIIII     "
    };

    public static final String[] Z = {
        "IIIIIIIIII ",
        "I    IIII  ",
        "   IIII    ",
        " IIII    I ",
        "IIIIIIIIII "
    };

    /**
     * @param word a word
     * @return the word transformed into fat letters
     */
    public static String[] fromString(String word) {
        word = word.toUpperCase();
        String[] fat = new String[5];
        int i = word.length();
        do {
            try {
                Field field;
                field = FatLetter.class.getDeclaredField(String.valueOf(word.charAt(i)));
                if (field != null) {
                    String[] c = (String[]) field.get(null);
                    fat[0] += c[0];
                    fat[1] += c[1];
                    fat[2] += c[2];
                    fat[3] += c[3];
                    fat[4] += c[4];
                }
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException ex) {
            }
            i--;
        } while (i > 0);
        return fat;
    }

}
