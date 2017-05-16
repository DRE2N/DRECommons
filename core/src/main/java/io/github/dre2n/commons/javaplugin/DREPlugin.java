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

import io.github.dre2n.commons.chat.MessageUtil;
import io.github.dre2n.commons.command.DRECommandCache;
import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.update.spigot.SpigotUpdater;
import org.mcstats.Metrics;

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

    private DRECommandCache commands;
    private Set<Inventory> guis = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;
        compat = CompatibilityHandler.getInstance();
        manager = getServer().getPluginManager();

        loadEconomyProvider();
        loadPermissionProvider();

        if (settings.usesMetrics()) {
            try {
                metrics = new Metrics(this);
                metrics.start();
            } catch (IOException exception) {
            }
        }

        if (settings.isSpigotMCResource()) {
            try {
                new SpigotUpdater(this, settings.getSpigotMCResourceId());
            } catch (IOException exception) {
            }
        }

        MessageUtil.log("&f[&9##########&f[&6" + getName() + "&f]&9##########&f]");
        MessageUtil.log("&fInternals: [" + (settings.getInternals().contains(compat.getInternals()) ? "&a" : "&4") + compat.getInternals() + "&f]");
        MessageUtil.log("&fSpigot API: [" + (!settings.requiresSpigot() || compat.isSpigot() ? "&a" : "&4") + compat.isSpigot() + "&f]");
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
     * @return the inventory GUIs
     */
    @Deprecated
    public Set<Inventory> getGUIs() {
        return guis;
    }

    /**
     * @param gui
     * the GUI to add
     */
    @Deprecated
    public void addGUI(Inventory gui) {
        guis.add(gui);
    }

    /**
     * @param gui
     * the GUI to remove
     */
    @Deprecated
    public void removeGUI(Inventory gui) {
        guis.remove(gui);
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
