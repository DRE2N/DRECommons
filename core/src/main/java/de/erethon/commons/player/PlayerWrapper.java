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

import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public interface PlayerWrapper {

    /**
     * @return
     * the Bukkit player
     */
    public Player getPlayer();

    /**
     * @return
     * the player's name
     */
    public String getName();

    /**
     * @return
     * the player's unique ID
     */
    public UUID getUniqueId();

}
