package io.github.dre2n.commons.javaplugin;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.config.CoreConfig;
import io.github.dre2n.commons.util.messageutil.MessageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CorePlugin extends JavaPlugin {
	
	private static CorePlugin plugin;
	
	private List<Internals> requirements = new ArrayList<Internals>();
	
	private CoreConfig coreConfig;
	private CompatibilityHandler compatibilityHandler;
	
	private Economy economyProvider;
	private Permission permissionProvider;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		loadCompatibilityHandler();
		loadEconomyProvider();
		loadPermissionProvider();
		
		MessageUtil.log("&f[&9##########&f[&6" + getName() + "&f]&9##########&f]");
		MessageUtil.log("&fInternals: [&e" + compatibilityHandler.getInternals() + "&f]");
		MessageUtil.log("&fSpigot API: [&e" + compatibilityHandler.isSpigot() + "&f]");
		MessageUtil.log("&fUUIDs: [&e" + compatibilityHandler.getInternals().useUUIDs() + "&f]");
		MessageUtil.log("&f[&9###############################&f]");
		
		if (compatibilityHandler.getInternals() == Internals.OUTDATED) {
			MessageUtil.log("&cWarning: Your CraftBukkit version is deprecated. " + getName() + " does not support it.");
		}
	}
	
	/**
	 * @return the plugin instance
	 */
	public static CorePlugin getPlugin() {
		return plugin;
	}
	
	/**
	 * @return the requirements
	 */
	public List<Internals> getRequirements() {
		return requirements;
	}
	
	/**
	 * @param requirements
	 * the minimum compatibilities this plugin needs to work
	 */
	public void setRequirements(List<Internals> requirements) {
		this.requirements = requirements;
	}
	
	/**
	 * @return the coreConfig
	 */
	public CoreConfig getCoreConfig() {
		return coreConfig;
	}
	
	/**
	 * load / reload a new instance of CoreConfig
	 */
	public void loadCoreConfig(File file) {
		coreConfig = new CoreConfig(file);
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
		compatibilityHandler = new CompatibilityHandler(coreConfig);
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
		try {
			if (coreConfig.useEconomy()) {
				RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
				if (economyProvider != null) {
					this.economyProvider = economyProvider.getProvider();
				}
			}
			
		} catch (NoClassDefFoundError error) {
			MessageUtil.log("&cCould not hook into Vault to register an economy provider!");
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
		try {
			RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
			if (permissionProvider != null) {
				this.permissionProvider = permissionProvider.getProvider();
			}
			
		} catch (NoClassDefFoundError error) {
			MessageUtil.log("&cCould not hook into Vault to register a permission provider!");
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
