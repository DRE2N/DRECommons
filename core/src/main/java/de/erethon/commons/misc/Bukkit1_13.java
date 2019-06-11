/*
 * Written in 2015-2019 by Daniel Saukel
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
package de.erethon.commons.misc;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

/**
 * Utils only available in 1.13+
 *
 * @author Daniel Saukel
 */
class Bukkit1_13 {

    static Block getAttachedBlock(Block block) {
        if (block.getBlockData() instanceof Directional) {
            Directional data = (Directional) block.getBlockData();
            if (data.getFaces().size() == 4) {
                return block.getRelative(data.getFacing().getOppositeFace());
            }
        }
        return block.getRelative(BlockFace.DOWN);
    }

}
