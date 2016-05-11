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

    static InternalsProvider internals = InternalsProvider.getInstance();

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
        return internals.setAttribute(itemStack, attributeName, value, slot);
    }

    /**
     * @param itemStack
     * a Bukkit ItemStack
     * @return
     * the ItemStack, but unbreakable
     */
    public static ItemStack setUnbreakable(ItemStack itemStack) {
        return internals.setUnbreakable(itemStack);
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
        return internals.setSkullOwner(itemStack, id, textureValue);
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
