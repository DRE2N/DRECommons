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
package io.github.dre2n.commons.compatibility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;

/**
 * @author Daniel Saukel
 */
public enum Internals {

    v1_12_R1(true),
    v1_11_R1(true),
    v1_10_R1(true),
    v1_9_R2(true),
    v1_9_R1(true),
    v1_8_R3(true),
    v1_8_R2(true),
    v1_8_R1(true),
    v1_7_R4(true),
    v1_7_R3(true),
    v1_7_R2(true),
    v1_7_R1(true),
    OUTDATED(true),
    NEW(true),
    UNKNOWN(false),
    GLOWSTONE(false);

    private boolean craftBukkitInternals;

    Internals(boolean craftBukkitInternals) {
        this.craftBukkitInternals = craftBukkitInternals;
    }

    /**
     * @return
     * true if the server (supposingly) uses CraftBukkit internals
     */
    public boolean usesCraftBukkitInternals() {
        return craftBukkitInternals;
    }

    @Override
    public String toString() {
        if (this == NEW) {
            return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        } else {
            return name();
        }
    }

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
            case v1_9_R2:
                andHigher.add(Internals.v1_9_R2);
            case v1_10_R1:
                andHigher.add(Internals.v1_10_R1);
            case v1_11_R1:
                andHigher.add(Internals.v1_11_R1);
            case v1_12_R1:
                andHigher.add(Internals.v1_12_R1);
            default:
                andHigher.add(NEW);
        }

        return andHigher;
    }

}
