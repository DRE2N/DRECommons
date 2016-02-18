package io.github.dre2n.commons.javaplugin;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.config.BRSettings;
import io.github.dre2n.commons.util.messageutil.MessageUtil;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CorePlugin extends JavaPlugin {
	
	protected static CorePlugin plugin;
	
	protected BRSettings settings;
	protected CompatibilityHandler compatibilityHandler;
	
	protected Economy economyProvider;
	protected Permission permissionProvider;
	
	@Override
	public void onEnable() {
		plugin = this;
		MessageUtil.log("&f[&9##########&f[&6" + getName() + "&f]&9##########&f]");
		
		loadCompatibilityHandler();
		MessageUtil.log("&fInternals: [&e" + compatibilityHandler.getInternals() + "&f]");
		MessageUtil.log("&fSpigot API: [&e" + compatibilityHandler.isSpigot() + "&f]");
		MessageUtil.log("&fUUIDs: [&e" + compatibilityHandler.getVersion().useUUIDs() + "&f]");
		
		if (compatibilityHandler.getInternals() == Internals.OUTDATED) {
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
		plugin = null;
	}
	
	/**
	 * @return the plugin instance
	 */
	public static CorePlugin getPlugin() {
		return plugin;
	}
	
	/**
	 * @return the settings
	 */
	public BRSettings getSettings() {
		return settings;
	}
	
	/**
	 * @return the loaded instance of CompatibilityHandler
	 */
	public CompatibilityHandler getCompatibilityHandler() {
		return compatibilityHandler;
	}
	
	/**
	 * load / reload a new instance of CompatibilityHandler
	 */
	public void loadCompatibilityHandler() {
		compatibilityHandler = new CompatibilityHandler(settings);
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
		if (settings.economy) {
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
		if (settings.permissions) {
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
	
}
