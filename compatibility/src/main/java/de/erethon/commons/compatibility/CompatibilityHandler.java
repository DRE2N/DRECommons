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

/**
 * The main class of CompatibilityHandler, mainly used for environment information.
 *
 * @author Daniel Saukel
 */
public class CompatibilityHandler {

    private static CompatibilityHandler instance;

    private Version version;
    private boolean spigot;
    private boolean paper;

    private CompatibilityHandler() {
        instance = this;

        version = Version.getByServer();
        spigot = Package.getPackage("org.spigotmc") != null;
        paper = Package.getPackage("com.destroystokyo.paper") != null;
    }

    /**
     * Creates a new instance if the statically saved instance is null
     *
     * @return the CompatibilityHandler instance
     */
    public static CompatibilityHandler getInstance() {
        if (instance == null) {
            new CompatibilityHandler();
        }

        return instance;
    }

    /**
     * Returns the Minecraft version
     *
     * @return the Minecraft version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Returns the package version of the internals
     *
     * @return the package version of the server internals
     */
    public Internals getInternals() {
        return version.getCraftBukkitInternals();
    }

    /**
     * Returns if the server software implements the Spigot API
     *
     * @return if the server software implements the Spigot API
     */
    public boolean isSpigot() {
        return spigot;
    }

    /**
     * Returns if the server software implements the PaperSpigot API
     *
     * @return if the server software implements the PaperSpigot API
     */
    public boolean isPaper() {
        return paper;
    }

}
