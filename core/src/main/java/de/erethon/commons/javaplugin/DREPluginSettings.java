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
package de.erethon.commons.javaplugin;

import de.erethon.commons.compatibility.Internals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daniel Saukel
 */
public class DREPluginSettings {

    private boolean spigot;
    private boolean paper;
    private boolean uuid;
    private boolean economy;
    private boolean permissions;
    private boolean metrics;
    private int resourceId = -1;
    private Set<Internals> internals;

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, Set<Internals> internals) {
        this(spigot, false, uuid, economy, permissions, metrics, internals);
    }

    public DREPluginSettings(boolean spigot, boolean paper, boolean uuid, boolean economy, boolean permissions, boolean metrics, Set<Internals> internals) {
        this.spigot = spigot;
        this.paper = paper;
        this.uuid = uuid;
        this.economy = economy;
        this.permissions = permissions;
        this.metrics = metrics;
        this.internals = internals;
    }

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, Internals... internals) {
        this(spigot, uuid, economy, permissions, metrics, new HashSet<>(Arrays.asList(internals)));
    }

    public DREPluginSettings(boolean spigot, boolean paper, boolean uuid, boolean economy, boolean permissions, boolean metrics, Internals... internals) {
        this(spigot, paper, uuid, economy, permissions, metrics, new HashSet<>(Arrays.asList(internals)));
    }

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, int resourceId, Set<Internals> internals) {
        this(spigot, uuid, economy, permissions, metrics, internals);
        this.resourceId = resourceId;
    }

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, int resourceId, Internals... internals) {
        this(spigot, uuid, economy, permissions, metrics, internals);
        this.resourceId = resourceId;
    }

    public DREPluginSettings(boolean spigot, boolean paper, boolean uuid, boolean economy, boolean permissions, boolean metrics, int resourceId, Set<Internals> internals) {
        this(spigot, paper, uuid, economy, permissions, metrics, internals);
        this.resourceId = resourceId;
    }

    public DREPluginSettings(boolean spigot, boolean paper, boolean uuid, boolean economy, boolean permissions, boolean metrics, int resourceId, Internals... internals) {
        this(spigot, paper, uuid, economy, permissions, metrics, internals);
        this.resourceId = resourceId;
    }

    /**
     * @return
     * if this plugin requires the Spigot API
     */
    public boolean requiresSpigot() {
        return spigot;
    }

    /**
     * @return
     * if this plugin requires the PaperSpigot API
     */
    public boolean requiresPaper() {
        return paper;
    }

    /**
     * @return
     * if this plugin requires UUID support
     */
    public boolean requiresUUID() {
        return uuid;
    }

    /**
     * @return
     * if this plugin requires the economy API of Vault
     */
    public boolean requiresVaultEconomy() {
        return economy;
    }

    /**
     * @return
     * if this plugin requires the permission API of Vault
     */
    public boolean requiresVaultPermissions() {
        return permissions;
    }

    /**
     * @return
     * if this plugin uses Metrics
     */
    public boolean usesMetrics() {
        return metrics;
    }

    /**
     * @return
     * if there is a resource thread at SpigotMC.org
     */
    public boolean isSpigotMCResource() {
        return resourceId != -1;
    }

    /**
     * @return
     * the SpigotMC.org resource ID or -1 if there is no thread
     */
    public int getSpigotMCResourceId() {
        return resourceId;
    }

    /**
     * @return
     * the internals supported by this plugin
     */
    public Set<Internals> getInternals() {
        return internals;
    }

}
