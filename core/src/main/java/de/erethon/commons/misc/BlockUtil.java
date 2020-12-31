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
package de.erethon.commons.misc;

import de.erethon.commons.compatibility.Version;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.bukkit.block.BlockFace.*;

/**
 * @author Daniel Saukel
 */
public class BlockUtil {

    private static final Map<String, BlockFace> LETTERS_TO_BLOCK_FACE = new HashMap<>();
    private static final Map<String, Integer> LETTERS_TO_YAW = new HashMap<>();
    private static final Map<BlockFace, Integer> BLOCK_FACE_TO_YAW = new HashMap<>();

    private static final boolean is1_13 = Version.isAtLeast(Version.MC1_13);

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
    public static Integer lettersToYaw(String letters) {
        return LETTERS_TO_YAW.get(letters.toUpperCase());
    }

    /**
     * Returns the corresponding yaw value of a block face.
     *
     * @param face the BlockFace
     * @return the corresponding yaw value
     */
    public static Integer blockFaceToYaw(BlockFace face) {
        return BLOCK_FACE_TO_YAW.get(face);
    }

    /**
     * @param block the block to check
     * @return the attached block
     */
    public static Block getAttachedBlock(Block block) {
        if (is1_13) {
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

        int topBlockX = Math.max(block1.getX(), block2.getX());
        int bottomBlockX = Math.min(block1.getX(), block2.getX());

        int topBlockY = Math.max(block1.getY(), block2.getY());
        int bottomBlockY = Math.min(block1.getY(), block2.getY());

        int topBlockZ = Math.max(block1.getZ(), block2.getZ());
        int bottomBlockZ = Math.min(block1.getZ(), block2.getZ());

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

    /**
     * @param block the block to check
     * @return true if the block is an Sign or WallSign, false otherwise
     */
    public static boolean isSignOrWallSign(Block block) {
        return isSign(block) | isWallSign(block);
    }

    /**
     * @param block the block to check
     * @return true if the block is an Sign, false otherwise
     */
    public static boolean isSign(Block block) {
        if (is1_13) {
            return block.getState() instanceof Sign;
        }
        return block.getType().name().contains("SIGN") && !block.getType().name().contains("WALL");
    }

    /**
     * @param block the block to check
     * @return true if the block is an WallSign, false otherwise
     */
    public static boolean isWallSign(Block block) {
        if (is1_13) {
            return block.getBlockData() instanceof WallSign;
        }
        return block.getType().name().equalsIgnoreCase("WALL_SIGN");
    }

    /**
     * Returns an {@link Set} of signs which are attached to the block.
     * If no signs are found, this will return an empty Set.
     * This method will ignore attached WallSigns till 1.13 or higher, due to compatibility issues.
     *
     * @param block the block to check
     * @return an Set of to the block attached signs
     */
    public static Set<Sign> getSignsAttachedTo(Block block) {
        Set<Sign> signs = new HashSet<>();
        Location location = block.getLocation();
        Location[] locations = new Location[]{
                location.clone().add(1, 0, 0),
                location.clone().add(0, 0, 1),
                location.clone().subtract(1, 0, 0),
                location.clone().subtract(0, 0, 1)
        };
        BlockFace[] faces = new BlockFace[]{WEST, NORTH, EAST, SOUTH};

        Location locUp = location.clone().add(0, 1, 0);
        if (isSign(locUp.getBlock())) {
            if (!isWallSign(locUp.getBlock())) {
                signs.add((Sign) locUp.getBlock().getState());
            }
        }
        if (!is1_13) {
            return signs;
        }
        for (int i = 0; i < locations.length; i++) {
            Block attached = locations[i].getBlock();
            if (isWallSign(attached)) {
                WallSign wallSign = (WallSign) attached.getBlockData();
                if (wallSign.getFacing().getOppositeFace().equals(faces[i])) {
                    signs.add((Sign) attached.getState());
                }
            }
        }
        return signs;
    }

    /**
     * Returns true if an Sign is attached to the block, false otherwise.
     * This method will ignore attached WallSigns till 1.13 or higher, due to compatibility issues.
     *
     * @param block the block to check
     * @return true if an sign is attached to the block, false otherwise
     */
    public static boolean isSignAttachedTo(Block block) {
        Location location = block.getLocation();
        Location[] locations = new Location[]{
                location.clone().add(1, 0, 0),
                location.clone().add(0, 0, 1),
                location.clone().subtract(1, 0, 0),
                location.clone().subtract(0, 0, 1)
        };
        BlockFace[] faces = new BlockFace[]{WEST, NORTH, EAST, SOUTH};

        Location locUp = location.clone().add(0, 1, 0);
        if (isSign(locUp.getBlock())) {
            if (!isWallSign(locUp.getBlock())) {
                return true;
            }
        }
        if (!is1_13) {
            return false;
        }
        for (int i = 0; i < locations.length; i++) {
            Block b = locations[i].getBlock();
            if (isWallSign(b)) {
                WallSign wallSign = (WallSign) b.getBlockData();
                if (wallSign.getFacing().getOppositeFace().equals(faces[i])) {
                    return true;
                }
            }
        }
        return false;
    }
}
