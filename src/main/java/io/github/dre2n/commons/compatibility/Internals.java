/*
 * Copyright (C) 2015-2016 Daniel Saukel
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
package io.github.dre2n.commons.compatibility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Daniel Saukel
 */
public enum Internals {

    v1_9_R1,
    v1_8_R3,
    v1_8_R2,
    v1_8_R1,
    v1_7_R4,
    v1_7_R3,
    v1_7_R2,
    v1_7_R1,
    GLOWSTONE,
    OUTDATED,
    UNKNOWN;

    /* Statics */
    public static final Set<Internals> INDEPENDENT = new HashSet<>(Arrays.asList(Internals.values()));

    /**
     * @param internals
     * the oldest internals in the Set
     */
    public static Set<Internals> andHigher(Internals internals) {
        Set<Internals> andHigher = new HashSet<>();

        switch (internals) {
            case v1_7_R1:
                andHigher.add(Internals.v1_7_R1);
            case v1_7_R2:
                andHigher.add(Internals.v1_7_R2);
            case v1_7_R3:
                andHigher.add(Internals.v1_7_R3);
            case v1_7_R4:
                andHigher.add(Internals.v1_7_R4);
            case v1_8_R1:
                andHigher.add(Internals.v1_8_R1);
            case v1_8_R2:
                andHigher.add(Internals.v1_8_R2);
            case v1_8_R3:
                andHigher.add(Internals.v1_8_R3);
            case v1_9_R1:
                andHigher.add(Internals.v1_9_R1);
            default:
                break;
        }

        return andHigher;
    }

}
