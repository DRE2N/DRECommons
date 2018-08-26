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

import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Internals;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class PlayerUtil {

    static InternalsProvider internals;

    static {
        switch (CompatibilityHandler.getInstance().getInternals()) {
            /*case GLOWSTONE:
                internals = new Glowstone();*/
            case NEW:
                internals = new New();
                break;
            case v1_13_R2:
                internals = new v1_13_R2();
                break;
            case v1_13_R1:
                internals = new v1_13_R1();
                break;
            case v1_12_R1:
                internals = new v1_12_R1();
                break;
            case v1_11_R1:
                internals = new v1_11_R1();
                break;
            case v1_10_R1:
                internals = new v1_10_R1();
                break;
            case v1_9_R2:
                internals = new v1_9_R2();
                break;
            case v1_9_R1:
                internals = new v1_9_R1();
                break;
            case v1_8_R3:
                internals = new v1_8_R3();
                break;
            case v1_8_R2:
                internals = new v1_8_R2();
                break;
            case v1_8_R1:
                internals = new v1_8_R1();
                break;
            default:
                internals = new InternalsProvider(CompatibilityHandler.getInstance().isSpigot());
        }
    }

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

        if (Internals.isAtLeast(Internals.v1_11_R1)) {
            player.getPassengers().forEach(e -> player.removePassenger(e));
        } else {
            player.setPassenger(null);
        }

        player.teleport(location);
    }

    /**
     * Respawns the player. Fails if the player died in the same tick
     *
     * @param player
     * the player to respawn
     */
    public static void respawn(Player player) {
        if (player.getHealth() <= 0 && player.isOnline()) {
            internals.respawn(player);
        }
    }

    /**
     * @param player
     * the player to check
     * @return
     * the player's ping
     */
    public static int getPing(Player player) {
        return internals.getPing(player);
    }

}
