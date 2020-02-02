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
package de.erethon.commons.javaplugin;

import de.erethon.commons.compatibility.Internals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.inventivetalent.update.spiget.comparator.VersionComparator;

/**
 * @author Daniel Saukel
 */
public class DREPluginSettings {

    /**
     * Returns a utility object to build an instance
     *
     * @return a utility object to build an instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private boolean spigot = false;
        private boolean paper = false;
        private boolean economy = false;
        private boolean permissions = false;
        private boolean metrics = false;
        private int resourceId = -1;
        private Set<Internals> internals = Internals.INDEPENDENT;
        private VersionComparator versionComparator = VersionComparator.SEM_VER;

        Builder() {
        }

        public Builder spigot(boolean spigot) {
            this.spigot = spigot;
            return this;
        }

        public Builder paper(boolean paper) {
            this.paper = paper;
            return this;
        }

        public Builder economy(boolean economy) {
            this.economy = economy;
            return this;
        }

        public Builder permissions(boolean permissions) {
            this.permissions = permissions;
            return this;
        }

        public Builder metrics(boolean metrics) {
            this.metrics = metrics;
            return this;
        }

        public Builder spigotMCResourceId(int resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder internals(Set<Internals> internals) {
            this.internals = internals;
            return this;
        }

        public Builder internals(Internals... internals) {
            this.internals = new HashSet<>(Arrays.asList(internals));
            return this;
        }

        public Builder versionComparator(VersionComparator versionComparator) {
            this.versionComparator = versionComparator;
            return this;
        }

        public DREPluginSettings build() {
            return new DREPluginSettings(spigot, paper, economy, permissions, metrics, resourceId, internals, versionComparator);
        }

    }

    private boolean spigot;
    private boolean paper;
    private boolean economy;
    private boolean permissions;
    private boolean metrics;
    private int resourceId = -1;
    private Set<Internals> internals;
    private VersionComparator versionComparator;

    public DREPluginSettings(boolean spigot, boolean paper, boolean economy, boolean permissions, boolean metrics, int resourceId, Set<Internals> internals,
            VersionComparator versionComparator) {
        this.spigot = spigot;
        this.paper = paper;
        this.economy = economy;
        this.permissions = permissions;
        this.metrics = metrics;
        this.internals = internals;
        this.resourceId = resourceId;
        this.versionComparator = versionComparator;
    }

    /**
     * @return if this plugin requires the Spigot API
     */
    public boolean requiresSpigot() {
        return spigot;
    }

    /**
     * @return if this plugin requires the PaperSpigot API
     */
    public boolean requiresPaper() {
        return paper;
    }

    /**
     * @return if this plugin requires the economy API of Vault
     */
    public boolean requiresVaultEconomy() {
        return economy;
    }

    /**
     * @return if this plugin requires the permission API of Vault
     */
    public boolean requiresVaultPermissions() {
        return permissions;
    }

    /**
     * @return if this plugin uses Metrics
     */
    public boolean usesMetrics() {
        return metrics;
    }

    /**
     * @return if there is a resource thread at SpigotMC.org
     */
    public boolean isSpigotMCResource() {
        return resourceId != -1;
    }

    /**
     * @return the SpigotMC.org resource ID or -1 if there is no thread
     */
    public int getSpigotMCResourceId() {
        return resourceId;
    }

    /**
     * @return the internals supported by this plugin
     */
    public Set<Internals> getInternals() {
        return internals;
    }

    /**
     * @return the SpigetUpdate version comparator
     */
    public VersionComparator getVersionComparator() {
        return versionComparator;
    }

}
