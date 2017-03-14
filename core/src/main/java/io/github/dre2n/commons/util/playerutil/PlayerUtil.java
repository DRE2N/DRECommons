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
package io.github.dre2n.commons.util.playerutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class PlayerUtil {

    static InternalsProvider internals;

    static {
        switch (CompatibilityHandler.getInstance().getInternals()) {
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
        return internals.getPing(player);
    }

}
