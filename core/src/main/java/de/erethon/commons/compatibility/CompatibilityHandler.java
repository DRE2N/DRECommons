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

import org.bukkit.Bukkit;

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

        if (Package.getPackage("net.glowstone") != null) {
            internals = Internals.GLOWSTONE;

        } else if (Package.getPackage("net.minecraft.server.v1_12_R1") != null) {
            internals = Internals.v1_12_R1;

        } else if (Package.getPackage("net.minecraft.server.v1_11_R1") != null) {
            internals = Internals.v1_11_R1;

        } else if (Package.getPackage("net.minecraft.server.v1_10_R1") != null) {
            internals = Internals.v1_10_R1;

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
            Package server = Bukkit.getServer().getClass().getPackage();
            if (server.getName().matches("org.bukkit.craftbukkit.v1_[4-6]_.*")) {
                internals = Internals.OUTDATED;
            } else if (server.getName().matches("org.bukkit.craftbukkit.v1_[1-9][1-9]_R[1-9]")) {
                internals = Internals.NEW;
            }
        }

        if (internals == null) {
            internals = Internals.UNKNOWN;
        }

        version = Version.getByServer();
        try {
            spigot = Bukkit.getServer().getClass().getDeclaredMethod("spigot") != null;
        } catch (NoSuchMethodException | SecurityException ex) {
            spigot = false;
        }
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
