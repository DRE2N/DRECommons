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

import net.minecraft.server.v1_13_R2.DimensionManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class v1_13_R2 extends InternalsProvider {

    @Override
    void respawn(Player player) {
        ((CraftServer) Bukkit.getServer()).getServer().getPlayerList().moveToWorld(((CraftPlayer) player).getHandle(), DimensionManager.OVERWORLD, false);
    }

    @Override
    int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }

}