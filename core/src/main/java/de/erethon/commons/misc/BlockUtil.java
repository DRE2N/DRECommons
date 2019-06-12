/*
 * Written from 2015-2019 by Daniel Saukel
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

import de.erethon.commons.compatibility.Version;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import static org.bukkit.block.BlockFace.*;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

/**
 * @author Daniel Saukel
 */
public class BlockUtil {

    private static final Map<String, BlockFace> LETTERS_TO_BLOCK_FACE = new HashMap<>();
    private static final Map<String, Integer> LETTERS_TO_YAW = new HashMap<>();
    private static final Map<BlockFace, Integer> BLOCK_FACE_TO_YAW = new HashMap<>();

    static {
        LETTERS_TO_BLOCK_FACE.put("E", EAST);
        LETTERS_TO_BLOCK_FACE.put("ENE", EAST_NORTH_EAST);
        LETTERS_TO_BLOCK_FACE.put("ESE", EAST_SOUTH_EAST);
        LETTERS_TO_BLOCK_FACE.put("W", WEST);
        LETTERS_TO_BLOCK_FACE.put("WNW", WEST_NORTH_WEST);
        LETTERS_TO_BLOCK_FACE.put("WSW", WEST_SOUTH_WEST);
        LETTERS_TO_BLOCK_FACE.put("N", NORTH);
        LETTERS_TO_BLOCK_FACE.put("NE", NORTH_EAST);
        LETTERS_TO_BLOCK_FACE.put("NW", NORTH_WEST);
        LETTERS_TO_BLOCK_FACE.put("NNE", NORTH_NORTH_EAST);
        LETTERS_TO_BLOCK_FACE.put("NNW", NORTH_NORTH_WEST);
        LETTERS_TO_BLOCK_FACE.put("S", SOUTH);
        LETTERS_TO_BLOCK_FACE.put("SE", SOUTH_EAST);
        LETTERS_TO_BLOCK_FACE.put("SW", SOUTH_WEST);
        LETTERS_TO_BLOCK_FACE.put("SSE", SOUTH_SOUTH_EAST);
        LETTERS_TO_BLOCK_FACE.put("SSW", SOUTH_SOUTH_WEST);
        LETTERS_TO_BLOCK_FACE.put("U", UP);
        LETTERS_TO_BLOCK_FACE.put("D", DOWN);

        LETTERS_TO_YAW.put("E", -90);
        LETTERS_TO_YAW.put("ENE", -112);
        LETTERS_TO_YAW.put("ESE", -68);
        LETTERS_TO_YAW.put("W", 90);
        LETTERS_TO_YAW.put("WNW", 112);
        LETTERS_TO_YAW.put("WSW", 68);
        LETTERS_TO_YAW.put("N", 180);
        LETTERS_TO_YAW.put("NE", -135);
        LETTERS_TO_YAW.put("NW", 135);
        LETTERS_TO_YAW.put("NNE", -157);
        LETTERS_TO_YAW.put("NNW", 157);
        LETTERS_TO_YAW.put("S", 0);
        LETTERS_TO_YAW.put("SE", -45);
        LETTERS_TO_YAW.put("SW", 45);
        LETTERS_TO_YAW.put("SSE", -22);
        LETTERS_TO_YAW.put("SSW", 22);

        BLOCK_FACE_TO_YAW.put(EAST, -90);
        BLOCK_FACE_TO_YAW.put(EAST_NORTH_EAST, -112);
        BLOCK_FACE_TO_YAW.put(EAST_SOUTH_EAST, -68);
        BLOCK_FACE_TO_YAW.put(WEST, 90);
        BLOCK_FACE_TO_YAW.put(WEST_NORTH_WEST, 112);
        BLOCK_FACE_TO_YAW.put(WEST_SOUTH_WEST, 68);
        BLOCK_FACE_TO_YAW.put(NORTH, 180);
        BLOCK_FACE_TO_YAW.put(NORTH_EAST, -135);
        BLOCK_FACE_TO_YAW.put(NORTH_WEST, 135);
        BLOCK_FACE_TO_YAW.put(NORTH_NORTH_EAST, -157);
        BLOCK_FACE_TO_YAW.put(NORTH_NORTH_WEST, 157);
        BLOCK_FACE_TO_YAW.put(SOUTH, 0);
        BLOCK_FACE_TO_YAW.put(SOUTH_EAST, -45);
        BLOCK_FACE_TO_YAW.put(SOUTH_WEST, 45);
        BLOCK_FACE_TO_YAW.put(SOUTH_SOUTH_EAST, -22);
        BLOCK_FACE_TO_YAW.put(SOUTH_SOUTH_WEST, 22);
    }

    /**
     * Returns the corresponding BlockFace of a block face in its short form (e.g. NNW = NORTH_NORTH_WEST).
     *
     * @param letters the letters, not case-sensitive
     * @return the corresponding BlockFace
     */
    public static BlockFace lettersToBlockFace(String letters) {
        return LETTERS_TO_BLOCK_FACE.get(letters.toUpperCase());
    }

    /**
     * Returns the corresponding yaw value of a block face in its short form (e.g. NNW = NORTH_NORTH_WEST).
     *
     * @param letters the letters, not case-sensitive
     * @return the corresponding yaw value
     */
    public static int lettersToYaw(String letters) {
        return LETTERS_TO_YAW.get(letters.toUpperCase());
    }

    /**
     * Returns the corresponding yaw value of a block face.
     *
     * @param face the BlockFace
     * @return the corresponding yaw value
     */
    public static int blockFaceToYaw(BlockFace face) {
        return BLOCK_FACE_TO_YAW.get(face);
    }

    /**
     * @param block the block to check
     * @return the attached block
     */
    public static Block getAttachedBlock(Block block) {
        if (!Version.isAtLeast(Version.MC1_13)) {
            return Bukkit1_13.getAttachedBlock(block);

        } else {
            MaterialData meta = block.getState().getData();
            BlockFace blockFace = DOWN;
            if (meta instanceof Attachable) {
                blockFace = ((Attachable) meta).getAttachedFace();
            }
            return block.getRelative(blockFace);
        }
    }

    /**
     * @param block1 the first edge block
     * @param block2 the second edge block
     * @return a Set of all blocks between block1 and block2
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
