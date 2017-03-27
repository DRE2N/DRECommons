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
package io.github.dre2n.commons.util.playerutil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class New extends InternalsProvider {

    String ORG_BUKKIT_CRAFTBUKKIT = "org.bukkit.craftbukkit.";
    String NET_MINECRAFT_SERVER = "net.minecraft.server.";

    Class ENTITYPLAYER;
    Field ENTITYPLAYER_PING;

    Class CRAFTPLAYER;
    Method CRAFTPLAYER_GET_HANDLE;

    New(String version) {
        try {
            ORG_BUKKIT_CRAFTBUKKIT += version;
            NET_MINECRAFT_SERVER += version;

            ENTITYPLAYER = Class.forName(NET_MINECRAFT_SERVER + ".EntityPlayer");
            ENTITYPLAYER_PING = ENTITYPLAYER.getDeclaredField("ping");

            CRAFTPLAYER = Class.forName(ORG_BUKKIT_CRAFTBUKKIT + ".entity.CraftPlayer");
            CRAFTPLAYER_GET_HANDLE = CRAFTPLAYER.getDeclaredMethod("getHandle");

        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException exception) {
        }
    }

    @Override
    int getPing(Player player) {
        try {
            Object entityPlayer = CRAFTPLAYER_GET_HANDLE.invoke(player);
            return ENTITYPLAYER_PING.getInt(entityPlayer);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            return 0;
        }
    }

}
