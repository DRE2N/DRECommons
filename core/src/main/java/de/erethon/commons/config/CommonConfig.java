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

import de.erethon.commons.javaplugin.DREPlugin;
import java.io.File;

/**
 * @author Daniel Saukel
 */
public class CommonConfig extends DREConfig {

    public static final int CONFIG_VERSION = 1;

    private static CommonConfig instance;

    private boolean updaterEnabled = true;

    public CommonConfig(File file) {
        super(file, CONFIG_VERSION);

        if (initialize) {
            initialize();
        }
        load();
    }

    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    @Override
    public void initialize() {
        if (!config.contains("updaterEnabled")) {
            config.set("updaterEnabled", updaterEnabled);
        }
        save();
    }

    @Override
    public void load() {
        updaterEnabled = config.getBoolean("updaterEnabled", updaterEnabled);
    }

    public static CommonConfig getInstance() {
        if (instance == null) {
            instance = new CommonConfig(new File(DREPlugin.getInstance().getDataFolder().getParent() + "/commons", "config.yml"));
        }
        return instance;
    }

}
