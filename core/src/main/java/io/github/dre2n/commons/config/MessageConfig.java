/*
 * Copyright (C) 2012-2017 Frank Baumann
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

import io.github.dre2n.commons.chat.MessageUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Frank Baumann, Milan Albrecht, Daniel Saukel
 */
public class MessageConfig {

    private Class<? extends Message> messages;

    private File file;
    private FileConfiguration config;

    /**
     * @param messages
     * the enum that implements Message
     * @param file
     * the file to save the messages to
     */
    public MessageConfig(Class<? extends Message> messages, File file) {
        this.messages = messages;
        this.file = file;

        if (!file.exists()) {
            try {
                file.createNewFile();
                try {
                    config = (FileConfiguration) messages.getDeclaredMethod("toConfig").invoke(null);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                    MessageUtil.log("[DRECommons] &cAn error occurred: Could not access method &rtoConfig&c.");
                }
                config.save(file);

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            config = YamlConfiguration.loadConfiguration(file);
            load();
        }

    }

    /**
     * Load the messages from file
     */
    public void load() {
        if (config != null) {
            Set<String> keySet = config.getKeys(true);
            for (String key : keySet) {
                Message message = null;
                try {
                    message = (Message) messages.getDeclaredMethod("getByIdentifier", String.class).invoke(null, key);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                    MessageUtil.log("[DRECommons] &cAn error occurred: Could not access method &rgetByIdentifier&c.");
                }
                if (message != null) {
                    message.setMessage(config.getString(key));
                }
            }
        }
    }

    /**
     * @return
     * if the file has been changed
     */
    public boolean changed() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        FileConfiguration freshConfig = null;
        try {
            freshConfig = (FileConfiguration) messages.getDeclaredMethod("toConfig").invoke(null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            MessageUtil.log("[DRECommons] &cAn error occurred: Could not access method &rtoConfig&c.");
        }

        if (!freshConfig.getValues(false).equals(config.getValues(false))) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * Save the data to the file and make a copy of the old data
     */
    public void save() {
        if (!changed()) {
            return;
        }

        String filePath = file.getPath();
        File oldMessages = new File(filePath.substring(0, filePath.length() - 4) + "_old.yml");

        try {
            try {
                ((FileConfiguration) messages.getDeclaredMethod("toConfig").invoke(null)).save(oldMessages);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                MessageUtil.log("[DRECommons] &cAn error occurred: Could not access method &rtoConfig&c.");
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @param message
     * the matching enum value from the Message implementation
     * @param args
     * the args to replace &v[i]
     * @return
     * the message String to send
     */
    public String getMessage(Message message, String... args) {
        String output = message.getMessage();

        int i = 0;
        for (String arg : args) {
            i++;

            if (arg != null) {
                output = output.replace("&v" + i, arg);

            } else {
                output = output.replace("&v" + i, "null");
            }
        }

        return output;
    }

}
