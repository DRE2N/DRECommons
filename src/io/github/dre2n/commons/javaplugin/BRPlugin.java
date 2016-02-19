package io.github.dre2n.commons.javaplugin;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BRPlugin extends JavaPlugin {
	
	private static BRPlugin instance;
	
	private static CompatibilityHandler compat;
	
	protected BRPluginSettings settings;
	
	private Economy economyProvider;
	private Permission permissionProvider;
	
	@Override
	public void onEnable() {
		instance = this;
		compat = CompatibilityHandler.getInstance();
		
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
		if (settings.requiresEconomy()) {
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
		if (settings.requiresPermissions()) {
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
