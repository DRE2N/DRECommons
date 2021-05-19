/*
 * Written from 2015-2021 by Daniel Saukel
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
package de.erethon.commons.chat;

import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
abstract class InternalsProvider {

    void sendActionBar(Player player, String message) {
    }

    abstract void sendTitle(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut);

}
