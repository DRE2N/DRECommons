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
package io.github.dre2n.commons.config;

import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public abstract class BRConfig {

    public final int CONFIG_VERSION;

    protected File file;
    protected FileConfiguration config;
    protected int configVersion;
    protected boolean initialize;

    public BRConfig(File file, int configVersion) {
        CONFIG_VERSION = configVersion;
        this.file = file;

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                config.set("configVersion", CONFIG_VERSION);
                initialize = true;
                save();

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            config = YamlConfiguration.loadConfiguration(file);
            this.configVersion = config.getInt("configVersion");

            if (this.configVersion != CONFIG_VERSION) {
                MessageUtil.log("The configuration file " + file.getName() + " seems to be outdated.");
                MessageUtil.log("Adding missing values...");

                initialize = true;
                config.set("configVersion", CONFIG_VERSION);
                save();
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

    /**
     * Save the configuration to the file
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /* Abstracts */
    /**
     * Initial setup
     */
    public abstract void initialize();

    /**
     * Load the config from file
     */
    public abstract void load();

}
