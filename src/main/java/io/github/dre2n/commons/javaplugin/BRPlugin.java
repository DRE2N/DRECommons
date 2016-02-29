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
package io.github.dre2n.commons.javaplugin;

import io.github.dre2n.commons.command.BRCommands;
import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public abstract class BRPlugin extends JavaPlugin {

    private static BRPlugin instance;

    protected static CompatibilityHandler compat;
    protected static PluginManager manager;

    protected BRPluginSettings settings;

    private Economy economyProvider;
    private Permission permissionProvider;

    private BRCommands commands;

    @Override
    public void onEnable() {
        instance = this;
        compat = CompatibilityHandler.getInstance();
        manager = getServer().getPluginManager();

        MessageUtil.log("&f[&9##########&f[&6" + getName() + "&f]&9##########&f]");
        MessageUtil.log("&fInternals: [&e" + compat.getInternals() + "&f]");
        MessageUtil.log("&fSpigot API: [&e" + compat.isSpigot() + "&f]");
        MessageUtil.log("&fUUIDs: [&e" + compat.getVersion().useUUIDs() + "&f]");

        if (compat.getInternals() == Internals.OUTDATED) {
            MessageUtil.log("&cWarning: Your CraftBukkit version is deprecated. " + getName() + " does not support it.");
        }

        loadEconomyProvider();
        MessageUtil.log("&fEconomy: [&e" + (economyProvider != null) + "&f]");

        loadPermissionProvider();
        MessageUtil.log("&fPermissions: [&e" + (permissionProvider != null) + "&f]");

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
    public static BRPlugin getInstance() {
        return instance;
    }

    /**
     * @return the settings
     */
    public BRPluginSettings getSettings() {
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
                MessageUtil.log("&cCould not hook into Vault to register an economy provider!");
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
                MessageUtil.log("&cCould not hook into Vault to register a permission provider!");
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
     * the commands of the plugin
     */
    public BRCommands getCommands() {
        return commands;
    }

    /**
     * @param command
     * the command to set
     */
    public void setCommands(BRCommands commands) {
        this.commands = commands;
    }

}
