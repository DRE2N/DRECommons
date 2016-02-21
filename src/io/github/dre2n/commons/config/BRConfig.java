package io.github.dre2n.commons.config;

import io.github.dre2n.commons.util.messageutil.MessageUtil;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class BRConfig {
	
	public int CONFIG_VERSION;
	
	protected File file;
	protected FileConfiguration config;
	protected int configVersion;
	
	public BRConfig(File file) {
		this.file = file;
		
		if ( !file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				config = YamlConfiguration.loadConfiguration(file);
				config.set("configVersion", CONFIG_VERSION);
				initialize();
				
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			
		} else {
			config = YamlConfiguration.loadConfiguration(file);
			load();
			if (configVersion != CONFIG_VERSION) {
				MessageUtil.log("The configuration file " + file.getName() + " seems to be outdated.");
				MessageUtil.log("Adding missing values...");
				initialize();
				config.set("configVersion", CONFIG_VERSION);
			}
		}
	}
	
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * @return the config
	 */
	public FileConfiguration getConfig() {
		return config;
	}
	
	/**
	 * @return the configuration version
	 */
	public int getConfigVersion() {
		return configVersion;
	}
	
	// Abstracts
	
	/**
	 * Initial setup
	 */
	public abstract void initialize();
	
	/**
	 * Load the config from file
	 */
	public abstract void load();
	
}
