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
import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;

/**
 * @author Daniel Saukel
 */
class v1_13_R2 extends InternalsProvider {

    @Override
    Object buildPacketPlayOutChat(ChatMessageType type, String message) {
        return new PacketPlayOutChat(ChatSerializer.a(message), net.minecraft.server.v1_13_R2.ChatMessageType.a((byte) type.ordinal()));
    }

}
