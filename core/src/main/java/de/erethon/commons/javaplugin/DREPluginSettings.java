/*
 * Written from 2015-2021 by Daniel Saukel
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
        private boolean database = false;
        private boolean economy = false;
        private boolean permissions = false;
        private boolean metrics = false;
        private int spigotMCResourceId = -1;
        private int bStatsResourceId = -1;
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

        public Builder database(boolean database) {
            this.database = database;
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

        public Builder spigotMCResourceId(int spigotMCResourceId) {
            this.spigotMCResourceId = spigotMCResourceId;
            return this;
        }

        public Builder bStatsResourceId(int bStatsResourceId) {
            this.bStatsResourceId = bStatsResourceId;
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
            return new DREPluginSettings(spigot, paper, database, economy, permissions, metrics, spigotMCResourceId, bStatsResourceId, internals, versionComparator);
        }

    }

    private boolean spigot;
    private boolean paper;
    private boolean database;
    private boolean economy;
    private boolean permissions;
    private boolean metrics;
    private int spigotMCResourceId = -1;
    private int bStatsResourceId = -1;
    private Set<Internals> internals;
    private VersionComparator versionComparator;

    private DREPluginSettings(boolean spigot, boolean paper, boolean database, boolean economy, boolean permissions, boolean metrics, int spigotMCResourceId, int bStatsResourceId,
            Set<Internals> internals, VersionComparator versionComparator) {
        this.spigot = spigot;
        this.paper = paper;
        this.database = database;
        this.economy = economy;
        this.permissions = permissions;
        this.metrics = metrics;
        this.internals = internals;
        this.spigotMCResourceId = spigotMCResourceId;
        this.bStatsResourceId = bStatsResourceId;
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
     * @return if this plugin uses a database
     */
    public boolean usesDatabase() {
        return database;
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
        return spigotMCResourceId != -1;
    }

    /**
     * @return the SpigotMC.org resource ID or -1 if there is no thread
     */
    public int getSpigotMCResourceId() {
        return spigotMCResourceId;
    }

    /**
     * @return the bStats.org resource ID or -1 if the plugin does not send data
     */
    public int getBStatsResourceId() {
        return bStatsResourceId;
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
