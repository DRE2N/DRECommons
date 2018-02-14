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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel Saukel
 */
public class SimpleDateUtil {

    public static String ddMMMMyyyyhhmmss(Date date) {
        return new SimpleDateFormat("dd.MMMM.yyyy hh:mm:ss").format(date);
    }

    public static String ddMMMMyyyyhhmmss(long date) {
        return ddMMMMyyyyhhmmss(new Date(date));
    }

    public static String ddMMyyyyhhmm(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy hh:mm").format(date);
    }

    public static String ddMMyyyyhhmm(long date) {
        return ddMMyyyyhhmm(new Date(date));
    }

    public static String ddMMyyyy(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public static String ddMMyyyy(long date) {
        return ddMMyyyy(new Date(date));
    }

    public static String format(Date date, String formatting) {
        return new SimpleDateFormat(formatting).format(date);
    }

    public static String format(long date, String formatting) {
        return format(new Date(date), formatting);
    }

}
