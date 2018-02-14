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
public class EnumUtil {

    /**
     * @param enumClass
     * the enum
     * @param valueName
     * the name of the enum value
     * @return
     * if the enum value with this name is valid
     */
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String valueName) {
        return getEnum(enumClass, valueName) != null;
    }

    /**
     * @param enumClass
     * the enum
     * @param valueName
     * the name of the enum value
     * @return
     * the enum value if it exists. Not case-sensitive
     */
    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> enumClass, String valueName) {
        return getEnum(enumClass, valueName.toUpperCase());
    }

    /**
     * @param enumClass
     * the enum
     * @param valueName
     * the name of the enum value
     * @return
     * the enum value if it exists
     */
    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String valueName) {
        if (enumClass == null || valueName == null) {
            return null;
        }

        try {
            return Enum.valueOf(enumClass, valueName);

        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

}
