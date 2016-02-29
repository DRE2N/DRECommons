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

import static io.github.dre2n.commons.compatibility.Internals.*;
import org.bukkit.Bukkit;

/**
 * @author Daniel Saukel
 */
public enum Version {

    MC1_8_9(true, UNKNOWN),
    MC1_8_8(true, v1_8_R3),
    MC1_8_7(true, v1_8_R3),
    MC1_8_6(true, v1_8_R3),
    MC1_8_5(true, v1_8_R3),
    MC1_8_4(true, v1_8_R3),
    MC1_8_3(true, v1_8_R2),
    MC1_8_1(true, UNKNOWN),
    MC1_8(true, v1_8_R1),
    MC1_7_10(true, v1_7_R4),
    MC1_7_9(true, v1_7_R3),
    MC1_7_8(true, v1_7_R3),
    MC1_7_7(true, UNKNOWN),
    MC1_7_6(true, UNKNOWN),
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
    MC1_4_2(false, OUTDATED);

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
        return super.toString().replaceAll("_", ".").split("MC")[1];
    }

    /* Statics */
    /**
     * @return
     * the version string taken directly from the server translated into a Version
     */
    public static Version getByServer() {
        String versionString = Bukkit.getServer().getVersion().split("\\(MC: ")[1].split("\\)")[0];

        for (Version version : Version.values()) {
            if (version.toString().equals(versionString)) {
                return version;
            }
        }

        return null;
    }

}
