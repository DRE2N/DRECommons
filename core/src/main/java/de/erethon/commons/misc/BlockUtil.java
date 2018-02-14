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
package de.erethon.commons.misc;

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
