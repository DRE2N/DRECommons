/*
 * Copyright (C) 2016 Daniel Saukel
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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    private static InternalsProvider instance;

    static InternalsProvider getInstance() {
        switch (CompatibilityHandler.getInstance().getInternals()) {
            case v1_11_R1:
                instance = new v1_11_R1();
                break;
            case v1_10_R1:
                instance = new v1_10_R1();
                break;
            case v1_9_R2:
                instance = new v1_9_R2();
                break;
            case v1_9_R1:
                instance = new v1_9_R1();
                break;
            case v1_8_R3:
                instance = new v1_8_R3();
                break;
            case v1_8_R2:
                instance = new v1_8_R2();
                break;
            case v1_8_R1:
                instance = new v1_8_R1();
                break;
            case v1_7_R4:
                instance = new v1_7_R4();
                break;
            case v1_7_R3:
                instance = new v1_7_R3();
                break;
            default:
                instance = new InternalsProvider();
                break;
        }

        return instance;
    }

    Player getOfflinePlayer(String name, UUID uuid, Location location) {
        return Bukkit.getPlayer(uuid);
    }

}
