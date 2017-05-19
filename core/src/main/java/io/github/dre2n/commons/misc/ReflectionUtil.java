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
package io.github.dre2n.commons.misc;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.inventory.ItemStack;

/**
 * Contains some useful NMS / OBC fields, methods and classes.
 * These fields target the latest Minecraft version DRECommons supports.
 *
 * @author Daniel Saukel
 */
public class ReflectionUtil {

    /* META */
    public static String INTERNALS_VERSION = CompatibilityHandler.getInstance().getInternals().toString();
    public static String ORG_BUKKIT_CRAFTBUKKIT = "org.bukkit.craftbukkit." + INTERNALS_VERSION;
    public static String NET_MINECRAFT_SERVER = "net.minecraft.server." + INTERNALS_VERSION;

    /* ITEM STACK */
    public static Class ITEMSTACK;
    public static Method ITEMSTACK_GET_TAG;
    public static Method ITEMSTACK_SET_TAG;

    public static Class CRAFTITEMSTACK;
    public static Method CRAFTITEMSTACK_AS_BUKKIT_COPY;
    public static Method CRAFTITEMSTACK_AS_NMS_COPY;

    /* NBT */
    public static Class NBT_BASE;

    public static Class NBT_TAG_COMPOUND;
    public static Method NBT_TAG_COMPOUND_SET;

    public static Class NBT_TAG_LIST;
    public static Method NBT_TAG_LIST_ADD;

    public static Class NBT_TAG_STRING;
    public static Constructor NBT_TAG_STRING_CONSTRUCTOR;

    /* PLAYER */
    public static Class ENTITYPLAYER;
    public static Field ENTITYPLAYER_PING;

    public static Class CRAFTPLAYER;
    public static Method CRAFTPLAYER_GET_HANDLE;

    static {
        try {
            NBT_BASE = Class.forName(NET_MINECRAFT_SERVER + ".NBTBase");

            NBT_TAG_COMPOUND = Class.forName(NET_MINECRAFT_SERVER + ".NBTTagCompound");
            NBT_TAG_COMPOUND_SET = NBT_TAG_COMPOUND.getDeclaredMethod("set", String.class, NBT_BASE);

            NBT_TAG_LIST = Class.forName(NET_MINECRAFT_SERVER + ".NBTTagList");
            NBT_TAG_LIST_ADD = NBT_TAG_LIST.getDeclaredMethod("add", NBT_BASE);

            NBT_TAG_STRING = Class.forName(NET_MINECRAFT_SERVER + ".NBTTagString");
            NBT_TAG_STRING_CONSTRUCTOR = NBT_TAG_STRING.getConstructor(String.class);

            ITEMSTACK = Class.forName(NET_MINECRAFT_SERVER + ".ItemStack");
            ITEMSTACK_GET_TAG = ITEMSTACK.getDeclaredMethod("getTag");
            ITEMSTACK_SET_TAG = ITEMSTACK.getDeclaredMethod("setTag", NBT_TAG_COMPOUND);

            CRAFTITEMSTACK = Class.forName(ORG_BUKKIT_CRAFTBUKKIT + ".inventory.CraftItemStack");
            CRAFTITEMSTACK_AS_BUKKIT_COPY = CRAFTITEMSTACK.getDeclaredMethod("asBukkitCopy", ITEMSTACK);
            CRAFTITEMSTACK_AS_NMS_COPY = CRAFTITEMSTACK.getDeclaredMethod("asNMSCopy", ItemStack.class);

            ENTITYPLAYER = Class.forName(NET_MINECRAFT_SERVER + ".EntityPlayer");
            ENTITYPLAYER_PING = ENTITYPLAYER.getField("ping");

            CRAFTPLAYER = Class.forName(ORG_BUKKIT_CRAFTBUKKIT + ".entity.CraftPlayer");
            CRAFTPLAYER_GET_HANDLE = CRAFTPLAYER.getMethod("getHandle");

        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException exception) {
        }
    }

}
