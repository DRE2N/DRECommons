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

import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.UUID;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.MinecraftServer;
import net.minecraft.server.v1_9_R1.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftServer;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.entity.Player;

/**
 * @author Frank Baumann, Daniel Saukel
 */
class v1_9_R1 {

    static Player getOfflinePlayer(String name, UUID uuid) {
        Player pplayer = null;

        try {
            File playerFolder = new File(Bukkit.getWorlds().get(0).getWorldFolder(), "players");

            for (File playerFile : playerFolder.listFiles()) {
                String fileName = playerFile.getName();
                String playerName = fileName.substring(0, fileName.length() - 4);

                GameProfile profile = new GameProfile(uuid, playerName);

                if (playerName.trim().equalsIgnoreCase(name)) {
                    MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                    EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), profile, new PlayerInteractManager(server.getWorldServer(0)));
                    Player target = entity == null ? null : (Player) entity.getBukkitEntity();
                    if (target != null) {
                        target.loadData();
                        return target;
                    }
                }
            }

        } catch (Exception e) {
            return null;
        }

        return pplayer;
    }

    static Player getOfflinePlayer(String name, UUID uuid, Location location) {
        Player pplayer = null;

        try {
            File playerFolder = new File(Bukkit.getWorlds().get(0).getWorldFolder(), "players");

            for (File playerFile : playerFolder.listFiles()) {
                String fileName = playerFile.getName();
                String playerName = fileName.substring(0, fileName.length() - 4);

                GameProfile profile = new GameProfile(uuid, playerName);

                if (playerName.trim().equalsIgnoreCase(name)) {
                    MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                    EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), profile, new PlayerInteractManager(server.getWorldServer(0)));
                    entity.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                    entity.world = ((CraftWorld) location.getWorld()).getHandle();
                    Player target = entity == null ? null : (Player) entity.getBukkitEntity();
                    if (target != null) {
                        // target.loadData();
                        return target;
                    }
                }
            }

        } catch (Exception e) {
            return null;
        }

        return pplayer;
    }

}
