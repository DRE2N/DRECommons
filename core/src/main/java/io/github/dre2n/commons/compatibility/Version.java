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

import io.github.dre2n.commons.chat.MessageUtil;
import static io.github.dre2n.commons.compatibility.Internals.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;

/**
 * @author Daniel Saukel
 */
public enum Version {

    MC1_12_2(true, v1_12_R1),
    MC1_12_1(true, v1_12_R1),
    MC1_12(true, v1_12_R1),
    MC1_11_2(true, v1_11_R1),
    MC1_11_1(true, v1_11_R1),
    MC1_11(true, v1_11_R1),
    MC1_10_2(true, v1_10_R1),
    MC1_10_1(true, Internals.UNKNOWN),
    MC1_10(true, v1_10_R1),
    MC1_9_4(true, v1_9_R2),
    MC1_9_2(true, v1_9_R1),
    MC1_9(true, v1_9_R1),
    MC1_8_9(true, Internals.UNKNOWN),
    MC1_8_8(true, v1_8_R3),
    MC1_8_7(true, v1_8_R3),
    MC1_8_6(true, v1_8_R3),
    MC1_8_5(true, v1_8_R3),
    MC1_8_4(true, v1_8_R3),
    MC1_8_3(true, v1_8_R2),
    MC1_8_1(true, Internals.UNKNOWN),
    MC1_8(true, v1_8_R1),
    MC1_7_10(true, v1_7_R4),
    MC1_7_9(true, v1_7_R3),
    MC1_7_8(true, v1_7_R3),
    MC1_7_7(true, Internals.UNKNOWN),
    MC1_7_6(true, Internals.UNKNOWN),
    MC1_7_5(false, v1_7_R2),
    MC1_7_4(false, OUTDATED),
    MC1_7_2(false, v1_7_R1),
    MC1_6_4(false, OUTDATED),
    MC1_6_2(false, OUTDATED),
    MC1_6_1(false, OUTDATED),
    MC1_5_2(false, OUTDATED),
    MC1_5_1(false, OUTDATED),
    MC1_5(false, OUTDATED),
    MC1_4_7(false, OUTDATED),
    MC1_4_6(false, OUTDATED),
    MC1_4_5(false, OUTDATED),
    MC1_4_4(false, OUTDATED),
    MC1_4_2(false, OUTDATED),
    UNKNOWN(true, Internals.UNKNOWN);

    private boolean uuids;
    private Internals craftBukkitInternals;

    Version(boolean uuids, Internals craftBukkitInternals) {
        this.uuids = uuids;
        this.craftBukkitInternals = craftBukkitInternals;
    }

    /**
     * @return
     * if this version supports UUIDs
     */
    public boolean useUUIDs() {
        return uuids;
    }

    /**
     * @return
     * the internals if the implementation is CraftBukkit
     */
    public Internals getCraftBukkitInternals() {
        return craftBukkitInternals;
    }

    @Override
    public String toString() {
        String[] string = super.toString().replaceAll("_", ".").split("MC");

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
     * @return
     * the version string taken directly from the server translated into a Version
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
        MessageUtil.log("&4This Bukkit implementation does not use a known version format.");
        return UNKNOWN;
    }

    /**
     * @param version
     * the oldest version in the Set
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
            default:
                andHigher.add(UNKNOWN);
        }

        return andHigher;
    }

}
