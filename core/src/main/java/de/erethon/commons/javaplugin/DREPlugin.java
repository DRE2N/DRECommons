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

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.command.DRECommandCache;
import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.config.CommonConfig;
import de.erethon.commons.config.MessageConfig;
import de.erethon.commons.gui.PageGUICache;
import java.io.File;
import java.lang.reflect.Field;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.update.spiget.SpigetUpdate;
import org.inventivetalent.update.spiget.UpdateCallback;
import org.inventivetalent.update.spiget.comparator.VersionComparator;

/**
 * The custom JavaPlugin class.
 * It provides simplified registration of and access to features of Vault, Metrics and DRECommons.
 *
 * @author Daniel Saukel
 */
public abstract class DREPlugin extends JavaPlugin {

    private static DREPlugin instance;

    protected static CompatibilityHandler compat;
    protected static PluginManager manager;

    protected DREPluginSettings settings;

    private Economy economyProvider;
    private Permission permissionProvider;

    private Metrics metrics;

    protected MessageConfig messageConfig;
    private DRECommandCache commands;
    private PageGUICache pageGUIs;

    @Override
    public void onEnable() {
        instance = this;
        compat = CompatibilityHandler.getInstance();
        manager = getServer().getPluginManager();

        loadEconomyProvider();
        loadPermissionProvider();

        if (settings.usesMetrics()) {
            metrics = new Metrics(this);
        }

        if (settings.isSpigotMCResource() && CommonConfig.getInstance().isUpdaterEnabled()) {
            SpigetUpdate updater = new SpigetUpdate(this, settings.getSpigotMCResourceId());
            updater.setVersionComparator(VersionComparator.SEM_VER);
            updater.checkForUpdate(new UpdateCallback() {
                @Override
                public void updateAvailable(String newVersion, String downloadUrl, boolean hasDirectDownload) {
                    MessageUtil.log(DREPlugin.this, "A new version of " + getName() + " is available (" + newVersion + "). Download it here: " + downloadUrl);
                }

                @Override
                public void upToDate() {
                    MessageUtil.log(DREPlugin.this, "The plugin is up to date.");
                }
            });
        }

        loadPageGUICache();

        MessageUtil.log("&f[&9##########&f[&6" + getName() + "&f]&9##########&f]");
        MessageUtil.log("&fInternals: [" + (settings.getInternals().contains(compat.getInternals()) ? "&a" : "&4") + compat.getInternals() + "&f]");
        MessageUtil.log("&fSpigot API: [" + (!settings.requiresSpigot() || compat.isSpigot() ? "&a" : "&4") + compat.isSpigot() + "&f]");
        MessageUtil.log("&fPaperSpigot API: [" + (!settings.requiresPaper() || compat.isPaper() ? "&a" : "&4") + compat.isPaper() + "&f]");
        MessageUtil.log("&fUUIDs: [" + (!settings.requiresUUID() || compat.getVersion().useUUIDs() ? "&a" : "&4") + compat.getVersion().useUUIDs() + "&f]");
        MessageUtil.log("&fEconomy: [" + (!settings.requiresVaultEconomy() || economyProvider != null ? "&a" : "&4") + (economyProvider != null) + "&f]");
        MessageUtil.log("&fPermissions: [" + (!settings.requiresVaultPermissions() || permissionProvider != null ? "&a" : "&4") + (permissionProvider != null) + "&f]");
        MessageUtil.log("&fMetrics: [&e" + (metrics != null) + "&f]");
        MessageUtil.log("&fSpigotMC ID: [&e" + (settings.isSpigotMCResource() ? settings.getSpigotMCResourceId() : "none") + "&f]");
        MessageUtil.log("&f[&9###############################&f]");
    }

    @Override
    public void onDisable() {
        instance = null;
        manager = null;
    }

    /**
     * @return the plugin instance
     */
    public static DREPlugin getInstance() {
        return instance;
    }

    /**
     * @return the settings
     */
    public DREPluginSettings getSettings() {
        return settings;
    }

    /**
     * @return the loaded instance of Economy
     */
    public Economy getEconomyProvider() {
        return economyProvider;
    }

    /**
     * load / reload a new instance of Permission
     */
    public void loadEconomyProvider() {
        if (settings.requiresVaultEconomy()) {
            try {
                RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
                if (economyProvider != null) {
                    this.economyProvider = economyProvider.getProvider();
                }

            } catch (NoClassDefFoundError error) {
            }
        }
    }

    /**
     * @return the loaded instance of Permission
     */
    public Permission getPermissionProvider() {
        return permissionProvider;
    }

    /**
     * load / reload a new instance of Permission
     */
    public void loadPermissionProvider() {
        if (settings.requiresVaultPermissions()) {
            try {
                RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
                if (permissionProvider != null) {
                    this.permissionProvider = permissionProvider.getProvider();
                }

            } catch (NoClassDefFoundError error) {
            }
        }
    }

    /**
     * @param group
     * the group to be checked
     */
    public boolean isGroupEnabled(String group) {
        for (String anyGroup : permissionProvider.getGroups()) {
            if (anyGroup.equalsIgnoreCase(group)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return
     * the Metrics instance
     */
    public Metrics getMetrics() {
        return metrics;
    }

    /**
     * @return
     * the loaded instance of MessageConfig
     */
    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    /**
     * @return
     * the CommandCache of the plugin
     */
    public DRECommandCache getCommandCache() {
        return commands;
    }

    /**
     * @param commands
     * the CommandCache to set
     */
    public void setCommandCache(DRECommandCache commands) {
        this.commands = commands;
    }

    /**
     * @return
     * the loaded instance of PageGUICache
     */
    public PageGUICache getPageGUICache() {
        return pageGUIs;
    }

    /**
     * load / reload a new instance of PageGUICache
     */
    public void loadPageGUICache() {
        if (pageGUIs != null) {
            HandlerList.unregisterAll(pageGUIs);
        }
        pageGUIs = new PageGUICache();
        manager.registerEvents(pageGUIs, this);
    }

    protected void setDataFolder(File dataFolder) {
        try {
            Field field = JavaPlugin.class.getDeclaredField("dataFolder");
            field.setAccessible(true);
            field.set(this, dataFolder);

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            MessageUtil.log(this, "&cError: Could not set data folder!");
        }
    }

}
