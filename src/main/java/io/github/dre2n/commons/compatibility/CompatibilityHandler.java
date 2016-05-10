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

/**
 * @author Daniel Saukel
 */
public class CompatibilityHandler {

    private static CompatibilityHandler instance;

    private Version version;
    private Internals internals;
    private boolean spigot;

    public CompatibilityHandler() {
        instance = this;

        version = Version.getByServer();

        if (Package.getPackage("net.glowstone") != null) {
            internals = Internals.GLOWSTONE;

        } else if (Package.getPackage("net.minecraft.server.v1_9_R2") != null) {
            internals = Internals.v1_9_R2;

        } else if (Package.getPackage("net.minecraft.server.v1_9_R1") != null) {
            internals = Internals.v1_9_R1;

        } else if (Package.getPackage("net.minecraft.server.v1_8_R3") != null) {
            internals = Internals.v1_8_R3;

        } else if (Package.getPackage("net.minecraft.server.v1_8_R2") != null) {
            internals = Internals.v1_8_R2;

        } else if (Package.getPackage("net.minecraft.server.v1_8_R1") != null) {
            internals = Internals.v1_8_R1;

        } else if (Package.getPackage("net.minecraft.server.v1_7_R4") != null) {
            internals = Internals.v1_7_R4;

        } else if (Package.getPackage("net.minecraft.server.v1_7_R3") != null) {
            internals = Internals.v1_7_R3;

        } else if (Package.getPackage("net.minecraft.server.v1_7_R2") != null) {
            internals = Internals.v1_7_R2;

        } else if (Package.getPackage("net.minecraft.server.v1_7_R1") != null) {
            internals = Internals.v1_7_R1;

        } else {
            for (Package internal : Package.getPackages()) {
                if (internal.getName().matches("net.minecraft.server.v1_[4-6]_.*")) {
                    internals = Internals.OUTDATED;

                } else if (internal.getName().matches("net.minecraft.server.v1_[9-99]_R[2-99]")) {
                    internals = Internals.NEW;
                }
            }
        }

        if (internals == null) {
            internals = Internals.UNKNOWN;
        }

        spigot = Package.getPackage("org.spigotmc") != null;
    }

    /**
     * Creates a new instance if the statically saved instance is null.
     *
     * @return
     * the CompatibilityHandler instance
     */
    public static CompatibilityHandler getInstance() {
        if (instance == null) {
            new CompatibilityHandler();
        }

        return instance;
    }

    /**
     * @return
     * the Minecraft version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * @return
     * the package version of the server internals
     */
    public Internals getInternals() {
        return internals;
    }

    /**
     * @return
     * if the server software implements the Spigot API
     */
    public boolean isSpigot() {
        return spigot;
    }

}
