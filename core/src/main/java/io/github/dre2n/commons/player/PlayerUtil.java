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
package io.github.dre2n.commons.player;

import io.github.dre2n.commons.misc.ReflectionUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class PlayerUtil {

    /**
     * @param name
     * a player's name
     * @return
     * the player's UUID
     */
    public static UUID getUniqueIdFromName(String name) {
        OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(name);
        return player.getUniqueId();
    }

    /**
     * @param uuid
     * the player's UUID as a String
     * @return
     * the player's name
     */
    public static String getNameFromUniqueId(String uuid) {
        OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(UUID.fromString(uuid));
        return player.getName();
    }

    /**
     * @param string
     * a UUID as a String
     * @return
     * if the String can be converted to a UUID
     */
    public static boolean isValidUUID(String string) {
        if (string.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Forces the player to leave his vehicle before teleportation
     *
     * @param player
     * the player to teleport
     * @param location
     * the target location
     */
    public static void secureTeleport(Player player, Location location) {
        if (player.isInsideVehicle()) {
            player.leaveVehicle();
        }

        player.teleport(location);
    }

    /**
     * @param player
     * the player to check
     * @return
     * the player's ping
     */
    public static int getPing(Player player) {
        try {
            return ReflectionUtil.ENTITYPLAYER_PING.getInt(ReflectionUtil.CRAFTPLAYER_GET_HANDLE.invoke(player));
        } catch (NullPointerException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            return -1;
        }
    }

}
