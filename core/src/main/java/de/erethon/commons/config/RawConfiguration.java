/*
 * Written from 2015-2019 by Daniel Saukel
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

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

/**
 * @author Daniel Saukel
 */
public class RawConfiguration extends YamlConfiguration {

    DumperOptions yamlOptions = new DumperOptions();
    Representer yamlRepresenter = new YamlRepresenter();
    Yaml yaml = new Yaml(new YamlConstructor(), yamlRepresenter, yamlOptions);

    private Map args = new LinkedHashMap<>();

    @Override
    public void loadFromString(String contents) throws InvalidConfigurationException {
        Map input;
        try {
            input = (Map) yaml.load(contents);
        } catch (YAMLException exception) {
            throw new InvalidConfigurationException(exception);
        } catch (ClassCastException exception) {
            throw new InvalidConfigurationException("Top level is not a Map.");
        }

        String header = parseHeader(contents);
        if (header.length() > 0) {
            options().header(header);
        }

        if (input != null) {
            args = input;
            convertMapsToSections(input, this);
        }
    }

    public static RawConfiguration loadConfiguration(File file) {
        RawConfiguration config = new RawConfiguration();

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }

        return config;
    }

    /**
     * @return the pure YAML Map.
     */
    public Map getArgs() {
        return args;
    }

}
