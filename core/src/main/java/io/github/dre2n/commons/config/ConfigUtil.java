/*
 * Copyright (C) 2016-2017 Daniel Saukel
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

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author Daniel Saukel
 */
public class ConfigUtil {

    /**
     * @param config
     * the ConfigurationSection
     * @param path
     * the path
     * @return
     * a Map<String, Object> at this path or an empty one
     */
    public static Map<String, Object> getMap(ConfigurationSection config, String path) {
        return getMap(config, path, false);
    }

    /**
     * @param config
     * the ConfigurationSection
     * @param path
     * the path
     * @param deep
     * deep values
     * @return
     * a Map<String, Object> at this path or an empty one
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
