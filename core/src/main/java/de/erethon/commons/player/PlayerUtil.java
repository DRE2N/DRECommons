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
package de.erethon.commons.player;

import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.compatibility.CompatibilityHandler;
import de.erethon.commons.compatibility.Internals;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class PlayerUtil {

    static InternalsProvider internals;

    static {
        String packageName = PlayerUtil.class.getPackage().getName();
        String internalsName = CompatibilityHandler.getInstance().getInternals().toString();
        try {
            internals = (InternalsProvider) Class.forName(packageName + "." + internalsName).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
            internals = new InternalsProvider();
            MessageUtil.log(ChatColor.DARK_RED + "PlayerUtil could not find a valid implementation for " + internalsName + ".");
        }
    }

    /**
     * Returns the unique ID of the player that has the name
     *
     * @param name a player's name
     * @return the player's UUID
     */
    public static UUID getUniqueIdFromName(String name) {
        return Bukkit.getServer().getOfflinePlayer(name).getUniqueId();
    }

    /**
     * Returns the name of the player that has the unique ID
     *
     * @param uuid the player's UUID as a String
     * @return the player's name
     */
    public static String getNameFromUniqueId(String uuid) {
        return Bukkit.getServer().getOfflinePlayer(UUID.fromString(uuid)).getName();
    }

    /**
     * Returns if the String can be converted to a UUID
     *
     * @param string a UUID as a String
     * @return if the String can be converted to a UUID
     */
    public static boolean isValidUUID(String string) {
        return string.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
    }

    /**
     * Forces the player to leave his vehicle before teleportation
     *
     * @param player   the player to teleport
     * @param location the target location
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
     * @param player the player to respawn
     */
    public static void respawn(Player player) {
        if (player.getHealth() <= 0 && player.isOnline()) {
            internals.respawn(player);
        }
    }

    /**
     * Returns the player's ping
     *
     * @param player the player to check
     * @return the player's ping
     */
    public static int getPing(Player player) {
        return internals.getPing(player);
    }

    /**
     * Sends a packet to the player
     *
     * @param player the player
     * @param packet an NMS packet
     */
    public static void sendPacket(Player player, Object packet) {
        internals.sendPacket(player, packet);
    }

}
