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
package io.github.dre2n.commons.util.itemutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.util.NumberUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
public class ItemUtil {

    protected static CompatibilityHandler compat = CompatibilityHandler.getInstance();

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param attributeName
     * a Minecraft name of an attribute
     * @param value
     * the attribute value
     * @param slot
     * the attribute slot as if 1.9, may be null for earlier versions
     * @return
     * a new Bukkit ItemStack with the attribute
     */
    public static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value, Slot slot) {
        switch (compat.getInternals()) {
            case v1_9_R1:
                return v1_9_R1.setAttribute(itemStack, attributeName, value, slot);
            case v1_8_R3:
                return v1_8_R3.setAttribute(itemStack, attributeName, value);
            case v1_8_R2:
                return v1_8_R2.setAttribute(itemStack, attributeName, value);
            case v1_8_R1:
                return v1_8_R1.setAttribute(itemStack, attributeName, value);
            case v1_7_R4:
                return v1_7_R4.setAttribute(itemStack, attributeName, value);
            case v1_7_R3:
                return v1_7_R3.setAttribute(itemStack, attributeName, value);
            case v1_7_R2:
                return v1_7_R2.setAttribute(itemStack, attributeName, value);
            case v1_7_R1:
                return v1_7_R1.setAttribute(itemStack, attributeName, value);
            default:
                return itemStack;
        }
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @return
     * the ItemStack, but unbreakable
     */
    public static ItemStack setUnbreakable(ItemStack itemStack) {
        if (compat.isSpigot()) {
            itemStack.getItemMeta().spigot().setUnbreakable(true);
            return itemStack;
        }

        switch (compat.getInternals()) {
            case v1_9_R1:
                return v1_9_R1.setUnbreakable(itemStack);
            case v1_8_R3:
                return v1_8_R3.setUnbreakable(itemStack);
            case v1_8_R2:
                return v1_8_R2.setUnbreakable(itemStack);
            case v1_8_R1:
                return v1_8_R1.setUnbreakable(itemStack);
            case v1_7_R4:
                return v1_7_R4.setUnbreakable(itemStack);
            case v1_7_R3:
                return v1_7_R3.setUnbreakable(itemStack);
            case v1_7_R2:
                return v1_7_R2.setUnbreakable(itemStack);
            case v1_7_R1:
                return v1_7_R1.setUnbreakable(itemStack);
            default:
                return itemStack;
        }
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @param id
     * the UUID of the SkullOwner
     * @param textureValue
     * the texture value
     * @return 
     */
    public static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        switch (compat.getInternals()) {
            case v1_9_R1:
                return v1_9_R1.setSkullOwner(itemStack, id, textureValue);
            case v1_8_R3:
                return v1_8_R3.setSkullOwner(itemStack, id, textureValue);
            case v1_8_R2:
                return v1_8_R2.setSkullOwner(itemStack, id, textureValue);
            case v1_8_R1:
                return v1_8_R1.setSkullOwner(itemStack, id, textureValue);
            case v1_7_R4:
                return v1_7_R4.setSkullOwner(itemStack, id, textureValue);
            case v1_7_R3:
                return v1_7_R3.setSkullOwner(itemStack, id, textureValue);
            case v1_7_R2:
                return v1_7_R2.setSkullOwner(itemStack, id, textureValue);
            case v1_7_R1:
                return v1_7_R1.setSkullOwner(itemStack, id, textureValue);
            default:
                return itemStack;
        }
    }

    /**
     * @param string
     * the ID as an unknown string
     * @return
     * the proper item ID
     */
    @SuppressWarnings("deprecation")
    public static int getId(String string) {
        if (NumberUtil.parseInt(string) > 0) {
            return NumberUtil.parseInt(string);

        } else if (Material.getMaterial(string) != null) {
            return Material.getMaterial(string).getId();

        } else {
            return 267;
        }
    }

}
