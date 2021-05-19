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

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author Daniel Saukel
 */
public class ConfigUtil {

    /**
     * @param config the ConfigurationSection
     * @param path   the path
     * @return a Map<String, Object> at this path or an empty one
     */
    public static Map<String, Object> getMap(ConfigurationSection config, String path) {
        return getMap(config, path, false);
    }

    /**
     * @param config the ConfigurationSection
     * @param path   the path
     * @param deep   deep values
     * @return a Map<String, Object> at this path or an empty one
     */
    public static Map<String, Object> getMap(ConfigurationSection config, String path, boolean deep) {
        ConfigurationSection section = config.getConfigurationSection(path);
        if (section != null) {
            return section.getValues(deep);
        } else {
            return new HashMap<>();
        }
    }

}
