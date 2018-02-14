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
package de.erethon.commons.player;

import de.erethon.commons.misc.ReflectionUtil;
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
