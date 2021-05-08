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

import de.erethon.commons.javaplugin.DREPlugin;
import java.io.File;

/**
 * @author Daniel Saukel
 */
public class CommonConfig extends DREConfig {

    public static final int CONFIG_VERSION = 2;

    private static CommonConfig instance;

    private boolean updaterEnabled = true;

    private String host = "localhost";
    private String port = "3306";
    private String database = "db";
    private String username = "user";
    private String password = "password";

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

    public String getDBHost() {
        return host;
    }

    public String getDBPort() {
        return port;
    }

    public String getDBName() {
        return database;
    }

    public String getDBUsername() {
        return username;
    }

    public String getDBPassword() {
        return password;
    }

    @Override
    public void initialize() {
        if (!config.contains("updaterEnabled")) {
            config.set("updaterEnabled", updaterEnabled);
        }
        if (!config.contains("database.host")) {
            config.set("database.host", host);
        }
        if (!config.contains("database.port")) {
            config.set("database.port", port);
        }
        if (!config.contains("database.name")) {
            config.set("database.name", database);
        }
        if (!config.contains("database.username")) {
            config.set("database.username", username);
        }
        if (!config.contains("database.password")) {
            config.set("database.password", password);
        }
        save();
    }

    @Override
    public void load() {
        updaterEnabled = config.getBoolean("updaterEnabled", updaterEnabled);
        host = config.getString("database.host", host);
        port = config.getString("database.port", port);
        database = config.getString("database.name", database);
        username = config.getString("database.username", username);
        password = config.getString("database.password", password);
    }

    public static CommonConfig getInstance() {
        if (instance == null) {
            instance = new CommonConfig(new File(DREPlugin.getInstance().getDataFolder().getParent() + "/commons", "config.yml"));
        }
        return instance;
    }

}
