/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.commons.util.itemutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class InternalsProvider {

    private static InternalsProvider instance;

    static InternalsProvider getInstance() {
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
            case v1_7_R4:
                instance = new v1_7_R4();
            case v1_7_R3:
                instance = new v1_7_R3();
            case v1_7_R2:
                instance = new v1_7_R2();
            case v1_7_R1:
                instance = new v1_7_R1();
            default:
                instance = new InternalsProvider();
        }

        return instance;
    }

    ItemStack setAttribute(ItemStack itemStack, String attributeName, double value, Slot slot) {
        return itemStack;
    }

    ItemStack setAttribute(ItemStack itemStack, String attributeName, double value) {
        return setAttribute(itemStack, attributeName, value, null);
    }

    ItemStack setUnbreakable(ItemStack itemStack) {
        if (CompatibilityHandler.getInstance().isSpigot()) {
            itemStack.getItemMeta().spigot().setUnbreakable(true);
        }

        return itemStack;
    }

    ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        return itemStack;
    }

}
