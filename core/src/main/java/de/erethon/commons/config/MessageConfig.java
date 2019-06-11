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

import de.erethon.commons.chat.MessageUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Frank Baumann, Milan Albrecht, Daniel Saukel
 */
public class MessageConfig {

    private Method toConfig;
    private Method getByIdentifier;

    private File file;
    private FileConfiguration config;

    /**
     * @param messageProvider the enum that implements Message
     * @param file            the file to save the messages to
     */
    public MessageConfig(Class<? extends Message> messageProvider, File file) {
        fetchMethods(messageProvider);
        this.file = file;

        if (!file.exists()) {
            try {
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                file.createNewFile();
                config = toConfig();
                config.save(file);

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            config = YamlConfiguration.loadConfiguration(file);
            load();
        }
    }

    /* Reflection */
    private void fetchMethods(Class<? extends Message> messageProvider) {
        try {
            toConfig = messageProvider.getDeclaredMethod("toConfig");
            getByIdentifier = messageProvider.getDeclaredMethod("getByIdentifier", String.class);

        } catch (NoSuchMethodException | SecurityException exception) {
            MessageUtil.log("[DRECommons] &cAn error occurred: Could not fetch methods.");
            exception.printStackTrace();
        }
    }

    private FileConfiguration toConfig() {
        try {
            return (FileConfiguration) toConfig.invoke(null);

        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            MessageUtil.log("[DRECommons] &cAn error occurred: Could not access method &rtoConfig&c.");
            exception.printStackTrace();
            return null;
        }
    }

    private Message getByIdentifier(String key) {
        try {
            return (Message) getByIdentifier.invoke(null, key);

        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            MessageUtil.log("[DRECommons] &cAn error occurred: Could not access method &rtoConfig&c.");
            exception.printStackTrace();
            return null;
        }
    }

    /* Actions */
    /**
     * Load the messages from file
     */
    public void load() {
        if (config == null) {
            return;
        }

        Set<String> keySet = config.getKeys(true);
        for (String key : keySet) {
            Message message = getByIdentifier(key);
            if (message != null) {
                message.setMessage(config.getString(key));
            }
        }

        FileConfiguration freshConfig = toConfig();
        for (String key : freshConfig.getKeys(true)) {
            if (!config.contains(key)) {
                config.set(key, freshConfig.get(key));
            }
        }
        save();
    }

    /**
     * Save the data to the file
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
