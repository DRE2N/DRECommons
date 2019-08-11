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
package de.erethon.commons.compatibility;

import static de.erethon.commons.compatibility.Internals.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;

/**
 * This enumeration represents the Minecraft version and is supposed not to be implementation specific.
 *
 * @author Daniel Saukel
 */
public enum Version {

    MC1_14_4(true, true, true, v1_14_R1),
    MC1_14_3(true, true, true, v1_14_R1),
    MC1_14_2(true, true, true, v1_14_R1),
    MC1_14_1(true, true, true, v1_14_R1),
    MC1_14(true, true, true, v1_14_R1),
    MC1_13_2(true, true, true, v1_13_R2),
    MC1_13_1(true, true, true, v1_13_R2),
    MC1_13(true, true, true, v1_13_R1),
    MC1_12_2(true, true, false, v1_12_R1),
    MC1_12_1(true, true, false, v1_12_R1),
    MC1_12(true, true, false, v1_12_R1),
    MC1_11_2(true, true, false, v1_11_R1),
    MC1_11_1(true, true, false, v1_11_R1),
    MC1_11(true, true, false, v1_11_R1),
    MC1_10_2(true, false, false, v1_10_R1),
    MC1_10_1(true, false, false, UNKNOWN),
    MC1_10(true, false, false, v1_10_R1),
    MC1_9_4(true, false, false, v1_9_R2),
    MC1_9_2(true, false, false, v1_9_R1),
    MC1_9(true, false, false, v1_9_R1),
    MC1_8_9(true, false, false, UNKNOWN),
    MC1_8_8(true, false, false, v1_8_R3),
    MC1_8_7(true, false, false, v1_8_R3),
    MC1_8_6(true, false, false, v1_8_R3),
    MC1_8_5(true, false, false, v1_8_R3),
    MC1_8_4(true, false, false, v1_8_R3),
    MC1_8_3(true, false, false, v1_8_R2),
    MC1_8_1(true, false, false, UNKNOWN),
    MC1_8(true, false, false, v1_8_R1),
    MC1_7_10(true, false, false, v1_7_R4),
    MC1_7_9(true, false, false, v1_7_R3),
    MC1_7_8(true, false, false, v1_7_R3),
    MC1_7_7(true, false, false, UNKNOWN),
    MC1_7_6(true, false, false, UNKNOWN),
    MC1_7_5(false, false, false, v1_7_R2),
    MC1_7_4(false, false, false, OUTDATED),
    MC1_7_2(false, false, false, v1_7_R1),
    MC1_6_4(false, false, false, v1_6_R3),
    MC1_6_2(false, false, false, v1_6_R2),
    MC1_6_1(false, false, false, v1_6_R1),
    MC1_5_2(false, false, false, v1_5_R3),
    MC1_5_1(false, false, false, v1_5_R2),
    MC1_5(false, false, false, v1_5_R1),
    MC1_4_7(false, false, false, v1_4_R1),
    MC1_4_6(false, false, false, OUTDATED),
    MC1_4_5(false, false, false, OUTDATED),
    MC1_4_4(false, false, false, OUTDATED),
    MC1_4_2(false, false, false, OUTDATED),
    /**
     * Represents upcoming versions.<p>
     * getCraftBukkitInternals() might return a known package version or NEW.
     */
    NEW(true, true, true, Internals.NEW);

    private boolean uuids;
    private boolean newMobNames;
    private boolean newMaterials;
    private Internals craftBukkitInternals;

    Version(boolean uuids, boolean newMobNames, boolean newMaterials, Internals craftBukkitInternals) {
        this.uuids = uuids;
        this.newMobNames = newMobNames;
        this.newMaterials = newMaterials;
        this.craftBukkitInternals = craftBukkitInternals;
    }

    /**
     * Returns if this version supports UUIDs
     *
     * @return if this version supports UUIDs
     */
    public boolean useUUIDs() {
        return uuids;
    }

    /**
     * Returns if this version uses the mob String IDs introduced in Minecraft 1.11
     *
     * @return if this version uses the mob String IDs introduced in Minecraft 1.11
     */
    public boolean useNewMobNames() {
        return newMobNames;
    }

    /**
     * Returns if this version uses the material String IDs introduced in Minecraft 1.13
     *
     * @return if this version uses the material String IDs introduced in Minecraft 1.13
     */
    public boolean useNewMaterials() {
        return newMaterials;
    }

    /**
     * Returns the package version that CraftBukkit uses for this Minecraft version
     *
     * @return the package version that CraftBukkit uses for this Minecraft version
     */
    public Internals getCraftBukkitInternals() {
        if (this == NEW) {
            try {
                return Internals.valueOf(Internals.NEW.toString());
            } catch (IllegalArgumentException exception) {
                return Internals.NEW;
            }
        } else {
            return craftBukkitInternals;
        }
    }

    @Override
    public String toString() {
        String[] string = super.toString().replace("_", ".").split("MC");

        if (string.length == 2) {
            return string[1];

        } else {
            return string[0];
        }
    }

    /* Statics */
    public static final Set<Version> INDEPENDENT;

    static {
        INDEPENDENT = new HashSet<>(Arrays.asList(Version.values()));
    }

    /**
     * Returns the version String taken directly from the server
     *
     * @return the version string taken directly from the server
     */
    public static Version getByServer() {
        try {
            if (Package.getPackage("org.bukkit.craftbukkit") != null) {
                String versionString = Bukkit.getServer().getVersion().split("\\(MC: ")[1].split("\\)")[0];
                for (Version version : Version.values()) {
                    if (version.toString().equals(versionString)) {
                        return version;
                    }
                }

            } else if (Package.getPackage("net.glowstone") != null) {
                String versionString = Bukkit.getServer().getVersion().split("-")[2];
                for (Version version : Version.values()) {
                    if (version.name().replaceAll("_", ".").equals(versionString)) {
                        return version;
                    }
                }
            }

        } catch (ArrayIndexOutOfBoundsException exception) {
        }
        return NEW;
    }

    /**
     * Returns a Set of all versions that are equal to or higher than the provided version
     *
     * @param version the oldest version in the Set
     * @return a Set of all versions that are equal to or higher than the provided version
     */
    public static Set<Version> andHigher(Version version) {
        Set<Version> andHigher = new HashSet<>();

        switch (version) {
            case MC1_4_2:
                andHigher.add(Version.MC1_4_2);
            case MC1_4_4:
                andHigher.add(Version.MC1_4_4);
            case MC1_4_5:
                andHigher.add(Version.MC1_4_5);
            case MC1_4_6:
                andHigher.add(Version.MC1_4_6);
            case MC1_4_7:
                andHigher.add(Version.MC1_4_7);
            case MC1_5:
                andHigher.add(Version.MC1_5);
            case MC1_5_1:
                andHigher.add(Version.MC1_5_1);
            case MC1_5_2:
                andHigher.add(Version.MC1_5_2);
            case MC1_6_1:
                andHigher.add(Version.MC1_6_1);
            case MC1_6_2:
                andHigher.add(Version.MC1_6_2);
            case MC1_6_4:
                andHigher.add(Version.MC1_6_4);
            case MC1_7_2:
                andHigher.add(Version.MC1_7_2);
            case MC1_7_4:
                andHigher.add(Version.MC1_7_4);
            case MC1_7_5:
                andHigher.add(Version.MC1_7_5);
            case MC1_7_6:
                andHigher.add(Version.MC1_7_6);
            case MC1_7_7:
                andHigher.add(Version.MC1_7_7);
            case MC1_7_8:
                andHigher.add(Version.MC1_7_8);
            case MC1_7_9:
                andHigher.add(Version.MC1_7_9);
            case MC1_7_10:
                andHigher.add(Version.MC1_7_10);
            case MC1_8:
                andHigher.add(Version.MC1_8);
            case MC1_8_1:
                andHigher.add(Version.MC1_8_1);
            case MC1_8_3:
                andHigher.add(Version.MC1_8_3);
            case MC1_8_4:
                andHigher.add(Version.MC1_8_4);
            case MC1_8_5:
                andHigher.add(Version.MC1_8_5);
            case MC1_8_6:
                andHigher.add(Version.MC1_8_6);
            case MC1_8_7:
                andHigher.add(Version.MC1_8_7);
            case MC1_8_8:
                andHigher.add(Version.MC1_8_8);
            case MC1_8_9:
                andHigher.add(Version.MC1_8_9);
            case MC1_9:
                andHigher.add(Version.MC1_9);
            case MC1_9_2:
                andHigher.add(Version.MC1_9_2);
            case MC1_9_4:
                andHigher.add(Version.MC1_9_4);
            case MC1_10:
                andHigher.add(Version.MC1_10);
            case MC1_10_1:
                andHigher.add(Version.MC1_10_1);
            case MC1_10_2:
                andHigher.add(Version.MC1_10_2);
            case MC1_11:
                andHigher.add(Version.MC1_11);
            case MC1_11_1:
                andHigher.add(Version.MC1_11_1);
            case MC1_11_2:
                andHigher.add(Version.MC1_11_2);
            case MC1_12:
                andHigher.add(Version.MC1_12);
            case MC1_12_1:
                andHigher.add(Version.MC1_12_1);
            case MC1_12_2:
                andHigher.add(Version.MC1_12_2);
            case MC1_13:
                andHigher.add(Version.MC1_13);
            case MC1_13_1:
                andHigher.add(Version.MC1_13_1);
            case MC1_13_2:
                andHigher.add(Version.MC1_13_2);
            case MC1_14:
                andHigher.add(Version.MC1_14);
            case MC1_14_1:
                andHigher.add(Version.MC1_14_1);
            case MC1_14_2:
                andHigher.add(Version.MC1_14_2);
            case MC1_14_3:
                andHigher.add(Version.MC1_14_3);
            case MC1_14_4:
                andHigher.add(Version.MC1_14_4);
            default:
                andHigher.add(NEW);
        }

        return andHigher;
    }

    /**
     * Returns if the environment version is equal to or higher than the provided version
     *
     * @param version the minimum version to check
     * @return if the environment version is equal to or higher than the provided version
     */
    public static boolean isAtLeast(Version version) {
        return andHigher(version).contains(CompatibilityHandler.getInstance().getVersion());
    }

    /**
     * Returns if the environment version is equal to or lower than the provided version
     *
     * @param version the maximum version to check
     * @return if the environment version is equal to or lower than the provided version
     */
    public static boolean isAtMost(Version version) {
        return version == CompatibilityHandler.getInstance().getVersion() || !isAtLeast(version);
    }

}
