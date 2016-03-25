/*
 * Copyright (C) 2016 Daniel Saukel
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

import java.io.File;

/**
 * @author Daniel Saukel
 */
public class ServerConfig extends BRConfig {

    public static final int CONFIG_VERSION = 1;

    private String serverName = "A Minecraft Server";
    private String serverVersion = "1.0";
    private String changelogURL = "http://example.com";
    private String text = "&1&l[ ==== &4&o%name% &1&l=== ]\n&3Version: &o%version%\n&3Changelog: &o%changelog%";

    public ServerConfig(File file) {
        super(file, CONFIG_VERSION);

        if (initialize) {
            initialize();
        }
        load();
    }

    /**
     * @return
     * the name of the server
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @return
     * the version string of the server
     */
    public String getServerVersion() {
        return serverVersion;
    }

    /**
     * @return
     * a URL to a changelog
     */
    public String getChangelogURL() {
        return changelogURL;
    }

    /**
     * @return
     * the additional output for /version unformatted
     */
    public String getRawText() {
        return text;
    }

    /**
     * @return
     * the additional output for /version formatted
     */
    public String getText() {
        return text.replaceAll("%name%", serverName).replaceAll("%version%", serverVersion).replaceAll("%changelog%", changelogURL);
    }

    @Override
    public void initialize() {
        if (!config.contains("serverName")) {
            config.set("serverName", serverName);
        }

        if (!config.contains("serverVersion")) {
            config.set("serverVersion", serverVersion);
        }

        if (!config.contains("changelogURL")) {
            config.set("changelogURL", changelogURL);
        }

        if (!config.contains("text")) {
            config.set("text", text);
        }

        save();
    }

    @Override
    public void load() {
        if (config.contains("serverName")) {
            serverName = config.getString("serverName");
        }

        if (config.contains("serverVersion")) {
            serverVersion = config.getString("serverVersion");
        }

        if (config.contains("changelogURL")) {
            changelogURL = config.getString("changelogURL");
        }

        if (config.contains("text")) {
            text = config.getString("text");
        }
    }

}
