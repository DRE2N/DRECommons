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
package de.erethon.commons.chat;

import static de.erethon.commons.misc.ReflectionUtil.*;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class New extends InternalsProvider {

    @Override
    void sendActionBarMessage(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        try {
            Object messageComponent = CHAT_SERIALIZER_A.invoke(null, "{\"text\": \"" + message + "\"}");
            Object barPacket = PACKET_PLAY_OUT_CHAT_CONSTRUCTOR.newInstance(messageComponent, CHAT_MESSAGE_TYPE_GAME_INFO);
            Object connection = ENTITY_PLAYER_PLAYER_CONNECTION.get(CRAFT_PLAYER_GET_HANDLE.invoke(player));
            PLAYER_CONNECTION_SEND_PACKET.invoke(connection, barPacket);
        } catch (InstantiationException | NullPointerException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

}
