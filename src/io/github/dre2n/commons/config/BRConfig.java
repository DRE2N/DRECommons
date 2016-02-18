package io.github.dre2n.commons.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class BRConfig {
	
	private File file;
	private FileConfiguration config;
	
	public BRConfig() {
	}
	
	public BRConfig(File file) {
		this.file = file;
		
		if ( !file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
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
	
}
