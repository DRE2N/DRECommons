/*
 * Written from 2015-2018 by Daniel Saukel
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
                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
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
