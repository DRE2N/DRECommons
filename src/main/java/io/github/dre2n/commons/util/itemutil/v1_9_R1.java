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

import io.github.dre2n.commons.util.NumberUtil;
import net.minecraft.server.v1_9_R1.NBTTagByte;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.NBTTagDouble;
import net.minecraft.server.v1_9_R1.NBTTagInt;
import net.minecraft.server.v1_9_R1.NBTTagList;
import net.minecraft.server.v1_9_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class v1_9_R1 {

    static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value) {
        net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }
        NBTTagList modifiers = compound.getList("AttributeModifiers", 10);

        NBTTagCompound attribute = new NBTTagCompound();
        attribute.set("AttributeName", new NBTTagString(attributeName));
        attribute.set("Name", new NBTTagString("BR-" + attributeName));
        attribute.set("Amount", new NBTTagDouble(value));
        attribute.set("Operation", new NBTTagByte((byte) 0));
        attribute.set("UUIDLeast", new NBTTagInt(NumberUtil.generateRandomInt(1, 50000)));
        attribute.set("UUIDMost", new NBTTagInt(NumberUtil.generateRandomInt(50001, 100000)));

        modifiers.add(attribute);

        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value, Slot slot) {
        net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }
        NBTTagList modifiers = new NBTTagList();

        NBTTagCompound attribute = new NBTTagCompound();
        attribute.set("AttributeName", new NBTTagString(attributeName));
        attribute.set("Name", new NBTTagString("BR-" + attributeName));
        attribute.set("Amount", new NBTTagDouble(value));
        attribute.set("Operation", new NBTTagByte((byte) 0));
        attribute.set("UUIDLeast", new NBTTagInt(NumberUtil.generateRandomInt(1, 50000)));
        attribute.set("UUIDMost", new NBTTagInt(NumberUtil.generateRandomInt(50001, 100000)));
        attribute.set("Slot", new NBTTagString(slot.toString()));

        modifiers.add(attribute);

        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
        net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }

        NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.set("Id", new NBTTagString(id));
        NBTTagCompound properties = new NBTTagCompound();
        NBTTagList textures = new NBTTagList();
        NBTTagCompound value = new NBTTagCompound();
        value.set("Value", new NBTTagString(textureValue));
        textures.add(value);
        properties.set("textures", textures);
        skullOwner.set("Properties", properties);

        compound.set("SkullOwner", skullOwner);
        nmsStack.setTag(compound);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    static ItemStack setUnbreakable(ItemStack itemStack) {
        net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }

        compound.set("Unbreakable", new NBTTagInt(1));

        nmsStack.setTag(compound);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }

}
