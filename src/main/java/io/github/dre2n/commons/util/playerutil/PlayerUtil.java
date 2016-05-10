/*
 * Copyright (C) 2012-2016 Frank Baumann
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

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class PlayerUtil {

    protected static CompatibilityHandler compat = CompatibilityHandler.getInstance();

    /**
     * @param name
     * the player's name
     * @param uuid
     * the player's UUID
     * @param location
     * tge player's location
     * @return
     * the player (who is offline) as an actual Player
     */
    public static Player getOfflinePlayer(String name, UUID uuid, Location location) {
        if (compat.getInternals() == null) {
            return null;
        }

        switch (compat.getInternals()) {
            case v1_9_R2:
                return v1_9_R2.getOfflinePlayer(name, uuid, location);
            case v1_9_R1:
                return v1_9_R1.getOfflinePlayer(name, uuid, location);
            case v1_8_R3:
                return v1_8_R3.getOfflinePlayer(name, uuid, location);
            case v1_8_R2:
                return v1_8_R2.getOfflinePlayer(name, uuid, location);
            case v1_8_R1:
                return v1_8_R1.getOfflinePlayer(name, uuid, location);
            case v1_7_R4:
                return v1_7_R4.getOfflinePlayer(name, uuid, location);
            case v1_7_R3:
                return v1_7_R3.getOfflinePlayer(name, uuid, location);
            default:
                return null;
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

}
