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
package io.github.dre2n.commons.chat;

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
     * @param word
     * a word
     * @return
     * the word transformed into fat letters
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
