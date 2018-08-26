/*
 * Written in 2015-2018 by Daniel Saukel
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

import static de.erethon.commons.misc.ReflectionUtil.*;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class New extends InternalsProvider {

    @Override
    void respawn(Player player) {
        try {
            PLAYER_LIST_MOVE_TO_WORLD.invoke(PLAYER_LIST_INSTANCE, CRAFT_PLAYER_GET_HANDLE.invoke(player), 0, false);
        } catch (NullPointerException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    int getPing(Player player) {
        try {
            return ENTITY_PLAYER_PING.getInt(CRAFT_PLAYER_GET_HANDLE.invoke(player));
        } catch (NullPointerException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

}
