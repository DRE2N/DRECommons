/*
 * Copyright (C) 2015-2017 Daniel Saukel
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
package io.github.dre2n.commons.item;

import static io.github.dre2n.commons.misc.ReflectionUtil.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class New extends InternalsProvider {

    @Override
    ItemStack setAttribute(ItemStack itemStack, String attributeName, double amount, byte operation, Set<String> slots) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        try {
            Object nmsStack = CRAFTITEMSTACK_AS_NMS_COPY.invoke(null, itemStack);

            Object compound = ITEMSTACK_GET_TAG.invoke(nmsStack);
            if (compound == null) {
                compound = NBT_TAG_COMPOUND.newInstance();
            }

            Object skullOwner = NBT_TAG_COMPOUND.newInstance();
            NBT_TAG_COMPOUND_SET.invoke(skullOwner, "Id", NBT_TAG_STRING_CONSTRUCTOR.newInstance(id));
            Object properties = NBT_TAG_COMPOUND.newInstance();
            Object textures = NBT_TAG_LIST.newInstance();
            Object value = NBT_TAG_COMPOUND.newInstance();
            NBT_TAG_COMPOUND_SET.invoke(value, "Value", NBT_TAG_STRING_CONSTRUCTOR.newInstance(textureValue));
            NBT_TAG_LIST_ADD.invoke(textures, value);
            NBT_TAG_COMPOUND_SET.invoke(properties, "textures", textures);
            NBT_TAG_COMPOUND_SET.invoke(skullOwner, "Properties", properties);

            NBT_TAG_COMPOUND_SET.invoke(compound, "SkullOwner", skullOwner);
            ITEMSTACK_SET_TAG.invoke(nmsStack, compound);
            return (ItemStack) CRAFTITEMSTACK_AS_BUKKIT_COPY.invoke(null, nmsStack);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            return itemStack;
        }
    }

}
