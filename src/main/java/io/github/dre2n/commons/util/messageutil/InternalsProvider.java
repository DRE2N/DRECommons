/*
 * Copyright (C) 2015-2016 Daniel Saukel
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
package io.github.dre2n.commons.util.messageutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    private static InternalsProvider instance;

    static InternalsProvider getInstance() {
        if (instance == null) {
            switch (CompatibilityHandler.getInstance().getInternals()) {
                case v1_9_R2:
                    instance = new v1_9_R2();
                case v1_9_R1:
                    instance = new v1_9_R1();
                case v1_8_R3:
                    instance = new v1_8_R3();
                case v1_8_R2:
                    instance = new v1_8_R2();
                case v1_8_R1:
                    instance = new v1_8_R1();
                default:
                    instance = new InternalsProvider();
            }
        }

        return instance;
    }

    void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        MessageUtil.sendCenteredMessage(player, title);
        MessageUtil.sendCenteredMessage(player, subtitle);
    }

    void sendActionBarMessage(Player player, String message) {
        MessageUtil.sendCenteredMessage(player, message);
    }

    void sendItemBarMessage(Player player, String message) {
        MessageUtil.sendCenteredMessage(player, message);
    }

}
