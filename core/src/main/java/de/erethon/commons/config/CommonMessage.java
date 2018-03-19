/*
 * Written in 2015-2018 by Daniel Saukel
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
import de.erethon.commons.javaplugin.DREPlugin;
import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public enum CommonMessage implements Message {

    CMD_DOES_NOT_EXIST("cmd.doesNotExist", "&cThis command does not exist."),
    CMD_NO_CONSOLE_COMMAND("cmd.noPlayerCommand", "&cThis command may not be executed by the console."),
    CMD_NO_PERMISSION("cmd.noPermission", "&cYou do not have permission to use this command."),
    CMD_NO_PLAYER_COMMAND("cmd.noPlayerCommand", "&cThis command may not be executed by a player."),
    GUI_BACK("gui.back", "&6&lBACK"),
    GUI_NEXT_PAGE("gui.nextPage", "&6&lNEXT PAGE"),
    GUI_PREVIOUS_PAGE("gui.previousPage", "&6&lPREVIOUS PAGE");

    private String identifier;
    private String message;

    CommonMessage(String identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    private static final MessageConfig CONFIG = new MessageConfig(CommonMessage.class, new File(DREPlugin.getInstance().getDataFolder().getParent() + "/commons", "messages.yml"));

    /* Getters and setters */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public String getMessage(String... args) {
        return CONFIG.getMessage(this, args);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /* Actions */
    /**
     * Sends the message to the console.
     */
    public void debug() {
        MessageUtil.log(DREPlugin.getInstance(), getMessage());
    }

    /* Statics */
    /**
     * @param identifer
     * the identifer to set
     */
    public static Message getByIdentifier(String identifier) {
        for (Message message : values()) {
            if (message.getIdentifier().equals(identifier)) {
                return message;
            }
        }

        return null;
    }

    /**
     * @return a FileConfiguration containing all messages
     */
    public static FileConfiguration toConfig() {
        FileConfiguration config = new YamlConfiguration();
        for (CommonMessage message : values()) {
            config.set(message.getIdentifier(), message.message);
        }
        return config;
    }

}
