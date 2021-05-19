/*
 * Written from 2015-2021 by Daniel Saukel
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
package de.erethon.commons.config;

import de.erethon.commons.chat.MessageUtil;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public abstract class DREConfig {

    public final int CONFIG_VERSION;

    protected File file;
    protected FileConfiguration config;
    protected int configVersion;
    protected boolean initialize;

    public DREConfig(File file, int configVersion) {
        CONFIG_VERSION = configVersion;
        this.file = file;

        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                config.set("configVersion", CONFIG_VERSION);
                initialize = true;
                save();

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            config = new YamlConfiguration();
            try {
                config.load(file);
            } catch (IOException | InvalidConfigurationException exception) {
                MessageUtil.log("&4The configuration file &6" + file.getPath() + " &4seems to be erroneous.");
                MessageUtil.log("&4This is not a bug. Try to fix the configuration file with &6http://yamllint.com&4.");
                String path = file.getPath();
                file.renameTo(new File(path + "_backup_" + System.currentTimeMillis()));
                try {
                    file.createNewFile();
                    config.load(file);
                } catch (IOException | InvalidConfigurationException exception2) {
                    exception2.printStackTrace();
                }
                MessageUtil.log("&4The file has been regenerated. A backup of the erroneous file has been saved.");

                initialize = true;
            }

            this.configVersion = config.getInt("configVersion");

            if (this.configVersion != CONFIG_VERSION && !initialize) {
                MessageUtil.log("&4The configuration file &6" + file.getPath() + " &4seems to be outdated.");
                MessageUtil.log("&4Adding missing values...");
                initialize = true;
            }

            if (initialize) {
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
     * Initial setup
     */
    public void initialize() {
        config.set("configVersion", CONFIG_VERSION);
        save();
    }

    /**
     * Save the configuration to the file
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            MessageUtil.log("&4Could not save &6" + file.getPath() + "&4...");
        }
    }

    /* Abstracts */
    /**
     * Load the config from file
     */
    public abstract void load();

}
