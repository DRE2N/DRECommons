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
