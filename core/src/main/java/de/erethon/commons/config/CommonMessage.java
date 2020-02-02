/*
 * Written from 2015-2020 by Daniel Saukel
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

import com.google.common.io.Files;
import de.erethon.commons.javaplugin.DREPlugin;
import java.io.File;
import java.io.IOException;
import org.bukkit.plugin.Plugin;

/**
 * Messages used by this library.
 *
 * @author Daniel Saukel
 */
public enum CommonMessage implements Message {

    CMD_DOES_NOT_EXIST("cmd.doesNotExist"),
    CMD_NO_CONSOLE_COMMAND("cmd.noConsoleCommand"),
    CMD_NO_PERMISSION("cmd.noPermission"),
    CMD_NO_PLAYER_COMMAND("cmd.noPlayerCommand");

    private static MessageHandler messageHandler;

    static {
        Plugin plugin = DREPlugin.getInstance();
        File dest = new File(plugin.getDataFolder().getParent() + "/commons", "messages.yml");
        if (!dest.exists()) {
            dest.getParentFile().mkdir();
            plugin.saveResource("messages.yml", false);
            try {
                Files.move(new File(plugin.getDataFolder(), "messages.yml"), dest);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        messageHandler = new MessageHandler(dest);
        messageHandler.setDefaultLanguage("messages");
    }

    private String path;

    CommonMessage(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

}
