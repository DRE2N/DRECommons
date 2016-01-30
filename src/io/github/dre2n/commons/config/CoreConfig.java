package io.github.dre2n.commons.config;

import java.io.File;

public class CoreConfig extends BRConfig {
	
	private String language;
	private boolean compatibilityInternals;
	private boolean compatibilityUUID;
	private boolean compatibilitySpigot;
	private boolean economy;
	private boolean permissions;
	
	public CoreConfig(File file) {
		super(file);
		
		if (getConfig().contains("language")) {
			language = getConfig().getString("language");
		}
		
		if (getConfig().contains("compatibilityInternals")) {
			compatibilityInternals = getConfig().getBoolean("compatibilityInternals");
		}
		
		if (getConfig().contains("compatibilityUUID")) {
			compatibilityUUID = getConfig().getBoolean("compatibilityUUID");
		}
		
		if (getConfig().contains("compatibilitySpigot")) {
			compatibilitySpigot = getConfig().getBoolean("compatibilitySpigot");
		}
	}
	
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * @return whether or not the internals compatibility shall be used
	 */
	public boolean useCompatibilityInternals() {
		return compatibilityInternals;
	}
	
	/**
	 * @return whether or not the UUID compatibility shall be used
	 */
	public boolean useCompatibilityUUID() {
		return compatibilityUUID;
	}
	
	/**
	 * @return whether or not the Spigot API compatibility shall be used
	 */
	public boolean useCompatibilitySpigot() {
		return compatibilitySpigot;
	}
	
	/**
	 * @return whether or not an economy provider shall be used
	 */
	public boolean useEconomy() {
		return economy;
	}
	
	/**
	 * @return whether or not a permission provider shall be used
	 */
	public boolean usePermissions() {
		return permissions;
	}
	
}
