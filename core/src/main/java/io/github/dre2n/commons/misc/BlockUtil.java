/*
 * Copyright (C) 2016-2017 Daniel Saukel
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
package io.github.dre2n.commons.misc;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

/**
 * @author Daniel Saukel
 */
public class BlockUtil {

    /**
     * @param block
     * the block to check
     * @return
     * the attached block
     */
    public static Block getAttachedBlock(Block block) {
        MaterialData meta = block.getState().getData();
        BlockFace blockFace = BlockFace.DOWN;

        if (meta instanceof Attachable) {
            blockFace = ((Attachable) meta).getAttachedFace();
        }

        return block.getRelative(blockFace);
    }

    /**
     * @param block1
     * the first edge block
     * @param block2
     * the second edge block
     * @return
     * a Set of all blocks between block1 and block2
     */
    public static Set<Block> getBlocksBetween(Block block1, Block block2) {
        Set<Block> blocks = new HashSet<>();

        int topBlockX = (block1.getX() < block2.getX() ? block2.getX() : block1.getX());
        int bottomBlockX = (block1.getX() > block2.getX() ? block2.getX() : block1.getX());

        int topBlockY = (block1.getY() < block2.getY() ? block2.getY() : block1.getY());
        int bottomBlockY = (block1.getY() > block2.getY() ? block2.getY() : block1.getY());

        int topBlockZ = (block1.getZ() < block2.getZ() ? block2.getZ() : block1.getZ());
        int bottomBlockZ = (block1.getZ() > block2.getZ() ? block2.getZ() : block1.getZ());

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    Block block = block1.getWorld().getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

}
