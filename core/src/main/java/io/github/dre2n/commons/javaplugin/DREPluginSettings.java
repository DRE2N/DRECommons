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
package io.github.dre2n.commons.javaplugin;

import io.github.dre2n.commons.compatibility.Internals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Daniel Saukel
 */
public class DREPluginSettings {

    private boolean spigot;
    private boolean uuid;
    private boolean economy;
    private boolean permissions;
    private boolean metrics;
    private int resourceId = -1;
    private Set<Internals> internals;

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, Set<Internals> internals) {
        this.spigot = spigot;
        this.uuid = uuid;
        this.economy = economy;
        this.permissions = permissions;
        this.metrics = metrics;
        this.internals = internals;
    }

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, Internals... internals) {
        this(spigot, uuid, economy, permissions, metrics, new HashSet<>(Arrays.asList(internals)));
    }

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, int resourceId, Set<Internals> internals) {
        this(spigot, uuid, economy, permissions, metrics, internals);
        this.resourceId = resourceId;
    }

    public DREPluginSettings(boolean spigot, boolean uuid, boolean economy, boolean permissions, boolean metrics, int resourceId, Internals... internals) {
        this(spigot, uuid, economy, permissions, metrics, internals);
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
