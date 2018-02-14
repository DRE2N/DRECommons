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
package de.erethon.commons.chat;

import de.erethon.commons.chat.InternalsProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class Glowstone extends InternalsProvider {

    @Override
    void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        title = ChatColor.translateAlternateColorCodes('&', title);
        player.sendTitle(title, subtitle, fadeIn, show, fadeOut);
    }

    @Override
    void sendActionBarMessage(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        player.sendActionBar(message);
    }

}
