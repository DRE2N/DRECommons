/*
 * Written from 2015-2020 by Daniel Saukel
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
package de.erethon.commons.compatibility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;

/**
 * This enumeration represents package versions.
 *
 * @author Daniel Saukel
 */
public enum Internals {

    /**
     * Represents upcoming CraftBukkit versions.
     * <br>
     * toString() returns the actual internals version instead of "NEW"
     */
    NEW(true),
    v1_15_R1(true),
    v1_14_R1(true),
    v1_13_R2(true),
    v1_13_R1(true),
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
    v1_6_R3(true),
    v1_6_R2(true),
    v1_6_R1(true),
    v1_5_R3(true),
    v1_5_R2(true),
    v1_5_R1(true),
    v1_4_R1(true),
    /**
     * Represents internals that are older than CraftBukkit 1.4.7, which introduced the current package version system.
     */
    OUTDATED(true),
    /**
     * Represents an implementation other than CraftBukkit.
     */
    UNKNOWN(false);

    private boolean craftBukkitInternals;

    Internals(boolean craftBukkitInternals) {
        this.craftBukkitInternals = craftBukkitInternals;
    }

    /**
     * Returns if the server uses CraftBukkit internals
     *
     * @return true if the server uses CraftBukkit internals
     */
    public boolean useCraftBukkitInternals() {
        return craftBukkitInternals;
    }

    @Override
    public String toString() {
        if (this == NEW) {
            return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } else {
            return name();
        }
    }

    /* Statics */
    /**
     * Contains all values of this enumeration.
     */
    public static final Set<Internals> INDEPENDENT = new HashSet<>(Arrays.asList(Internals.values()));

    /**
     * Returns a Set of all internals that are equal to or higher than the environment version
     *
     * @param internals the oldest internals in the Set
     * @return a Set of all internals that are equal to or higher than the environment version
     */
    public static Set<Internals> andHigher(Internals internals) {
        Set<Internals> andHigher = new HashSet<>();
        Internals[] values = values();
        for (int i = 0; i <= internals.ordinal(); i++) {
            andHigher.add(values[i]);
        }
        return andHigher;
    }

    /**
     * Returns if the environment version is equal to or higher than the provided internals version
     *
     * @param internals the minimum internals version to check
     * @return if the environment version is equal to or higher than the provided internals version
     */
    public static boolean isAtLeast(Internals internals) {
        return andHigher(internals).contains(CompatibilityHandler.getInstance().getInternals());
    }

    /**
     * Returns if the environment version is equal to or lower than the provided internals version
     *
     * @param internals the maximum internals to check
     * @return if the environment version is equal to or lower than the internals version
     */
    public static boolean isAtMost(Internals internals) {
        return internals == CompatibilityHandler.getInstance().getInternals() || !isAtLeast(internals);
    }

}
