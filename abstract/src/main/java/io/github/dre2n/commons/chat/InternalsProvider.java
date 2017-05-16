/*
 * Copyright (C) 2015-2017 Daniel Saukel
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
package io.github.dre2n.commons.chat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    boolean spigot;

    InternalsProvider() {
    }

    InternalsProvider(boolean isSpigot) {
        spigot = isSpigot;
    }

    void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        title = ChatColor.translateAlternateColorCodes('&', title);
        player.sendTitle(title, subtitle, fadeIn, show, fadeOut);
    }

    void sendActionBarMessage(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        if (spigot) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        } else {
            player.sendMessage(message);
        }
    }

}
