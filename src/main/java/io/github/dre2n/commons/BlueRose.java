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
package io.github.dre2n.commons;

import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.config.ServerConfig;
import io.github.dre2n.commons.javaplugin.BRPlugin;
import io.github.dre2n.commons.javaplugin.BRPluginSettings;
import io.github.dre2n.commons.listener.PlayerListener;
import java.io.File;

/**
 * @author Daniel Saukel
 */
public class BlueRose extends BRPlugin {

    private static BlueRose instance;

    private ServerConfig config;

    public BlueRose() {
        settings = new BRPluginSettings(false, false, false, false, Internals.INDEPENDENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        instance = this;

        loadServerConfig(new File("BlueRose.yml"));

        manager.registerEvents(new PlayerListener(), this);
    }

    /**
     * @return
     * the plugin instance
     */
    public static BlueRose getInstance() {
        return instance;
    }

    /**
     * @return
     * the loaded instance of ServerConfig
     */
    public ServerConfig getServerConfig() {
        return config;
    }

    /**
     * load / reload a new instance of ServerConfig
     */
    public void loadServerConfig(File file) {
        config = new ServerConfig(file);
    }

}
