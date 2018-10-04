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

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    Object buildPacketPlayOutChat(ChatMessageType type, String message) {
        return null;
    }

    void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, show, fadeOut);
    }

    protected byte getNMSCMT(ChatMessageType type) {
        if (type == ChatMessageType.ACTION_BAR) {
            return 2;
        } else if (type == ChatMessageType.SYSTEM) {
            return 1;
        } else {
            return 0;
        }
    }

}
