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
package de.erethon.commons.compatibility;

import de.erethon.commons.chat.MessageUtil;
import static de.erethon.commons.compatibility.Internals.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;

/**
 * @author Daniel Saukel
 */
public enum Version {

    MC1_12_2(true, false, v1_12_R1),
    MC1_12_1(true, false, v1_12_R1),
    MC1_12(true, false, v1_12_R1),
    MC1_11_2(true, false, v1_11_R1),
    MC1_11_1(true, false, v1_11_R1),
    MC1_11(true, false, v1_11_R1),
    MC1_10_2(true, false, v1_10_R1),
    MC1_10_1(true, false, Internals.UNKNOWN),
    MC1_10(true, false, v1_10_R1),
    MC1_9_4(true, false, v1_9_R2),
    MC1_9_2(true, false, v1_9_R1),
    MC1_9(true, false, v1_9_R1),
    MC1_8_9(true, false, Internals.UNKNOWN),
    MC1_8_8(true, false, v1_8_R3),
    MC1_8_7(true, false, v1_8_R3),
    MC1_8_6(true, false, v1_8_R3),
    MC1_8_5(true, false, v1_8_R3),
    MC1_8_4(true, false, v1_8_R3),
    MC1_8_3(true, false, v1_8_R2),
    MC1_8_1(true, false, Internals.UNKNOWN),
    MC1_8(true, false, v1_8_R1),
    MC1_7_10(true, false, v1_7_R4),
    MC1_7_9(true, false, v1_7_R3),
    MC1_7_8(true, false, v1_7_R3),
    MC1_7_7(true, false, Internals.UNKNOWN),
    MC1_7_6(true, false, Internals.UNKNOWN),
    MC1_7_5(false, false, v1_7_R2),
    MC1_7_4(false, false, OUTDATED),
    MC1_7_2(false, false, v1_7_R1),
    MC1_6_4(false, false, OUTDATED),
    MC1_6_2(false, false, OUTDATED),
    MC1_6_1(false, false, OUTDATED),
    MC1_5_2(false, false, OUTDATED),
    MC1_5_1(false, false, OUTDATED),
    MC1_5(false, false, OUTDATED),
    MC1_4_7(false, false, OUTDATED),
    MC1_4_6(false, false, OUTDATED),
    MC1_4_5(false, false, OUTDATED),
    MC1_4_4(false, false, OUTDATED),
    MC1_4_2(false, false, OUTDATED),
    UNKNOWN(true, true, Internals.UNKNOWN);

    private boolean uuids;
    private boolean newMaterials;
    private Internals craftBukkitInternals;

    Version(boolean uuids, boolean newMaterials, Internals craftBukkitInternals) {
        this.uuids = uuids;
        this.newMaterials = newMaterials;
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
     * if this version uses 1.13+ materials
     */
    public boolean useNewMaterials() {
        return newMaterials;
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
