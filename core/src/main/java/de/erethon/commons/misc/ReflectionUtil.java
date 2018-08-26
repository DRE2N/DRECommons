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
package de.erethon.commons.misc;

import de.erethon.commons.compatibility.CompatibilityHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
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

    /* SERVER */
    public static Class CRAFT_SERVER;
    public static Class MINECRAFT_SERVER;
    public static Object MINECRAFT_SERVER_INSTANCE;

    /* ITEM STACK */
    public static Class ITEM_STACK;
    public static Method ITEM_STACK_GET_TAG;
    public static Method ITEM_STACK_SET_TAG;

    public static Class CRAFT_ITEM_STACK;
    public static Method CRAFT_ITEM_STACK_AS_BUKKIT_COPY;
    public static Method CRAFT_ITEM_STACK_AS_NMS_COPY;

    /* NBT */
    public static Class NBT_BASE;

    public static Class NBT_TAG_COMPOUND;
    public static Method NBT_TAG_COMPOUND_SET;

    public static Class NBT_TAG_LIST;
    public static Method NBT_TAG_LIST_ADD;

    public static Class NBT_TAG_STRING;
    public static Constructor NBT_TAG_STRING_CONSTRUCTOR;

    /* PLAYER */
    public static Class PLAYER_LIST;
    public static Object PLAYER_LIST_INSTANCE;
    public static Method PLAYER_LIST_MOVE_TO_WORLD;

    public static Class ENTITY_PLAYER;
    public static Field ENTITY_PLAYER_PING;
    public static Field ENTITY_PLAYER_PLAYER_CONNECTION;

    public static Class CRAFT_PLAYER;
    public static Method CRAFT_PLAYER_GET_HANDLE;

    public static Class PLAYER_CONNECTION;
    public static Method PLAYER_CONNECTION_SEND_PACKET;

    /* CHAT */
    public static Class I_CHAT_BASE_COMPONENT;

    public static Class CHAT_MESSAGE_TYPE;
    public static Field CHAT_MESSAGE_TYPE_CHAT;
    public static Field CHAT_MESSAGE_TYPE_GAME_INFO;
    public static Field CHAT_MESSAGE_TYPE_SYSTEM;

    public static Class CHAT_SERIALIZER;
    public static Method CHAT_SERIALIZER_A;

    public static Class PACKET_PLAY_OUT_CHAT;
    public static Constructor PACKET_PLAY_OUT_CHAT_CONSTRUCTOR;

    static {
        try {
            CRAFT_SERVER = Bukkit.getServer().getClass();
            MINECRAFT_SERVER = Class.forName(NET_MINECRAFT_SERVER + ".MinecraftServer");
            MINECRAFT_SERVER_INSTANCE = CRAFT_SERVER.getMethod("getServer").invoke(Bukkit.getServer());

            NBT_BASE = Class.forName(NET_MINECRAFT_SERVER + ".NBTBase");

            NBT_TAG_COMPOUND = Class.forName(NET_MINECRAFT_SERVER + ".NBTTagCompound");
            NBT_TAG_COMPOUND_SET = NBT_TAG_COMPOUND.getMethod("set", String.class, NBT_BASE);

            NBT_TAG_LIST = Class.forName(NET_MINECRAFT_SERVER + ".NBTTagList");
            NBT_TAG_LIST_ADD = NBT_TAG_LIST.getMethod("add", NBT_BASE);

            NBT_TAG_STRING = Class.forName(NET_MINECRAFT_SERVER + ".NBTTagString");
            NBT_TAG_STRING_CONSTRUCTOR = NBT_TAG_STRING.getConstructor(String.class);

            ITEM_STACK = Class.forName(NET_MINECRAFT_SERVER + ".ItemStack");
            ITEM_STACK_GET_TAG = ITEM_STACK.getMethod("getTag");
            ITEM_STACK_SET_TAG = ITEM_STACK.getMethod("setTag", NBT_TAG_COMPOUND);

            CRAFT_ITEM_STACK = Class.forName(ORG_BUKKIT_CRAFTBUKKIT + ".inventory.CraftItemStack");
            CRAFT_ITEM_STACK_AS_BUKKIT_COPY = CRAFT_ITEM_STACK.getMethod("asBukkitCopy", ITEM_STACK);
            CRAFT_ITEM_STACK_AS_NMS_COPY = CRAFT_ITEM_STACK.getMethod("asNMSCopy", ItemStack.class);

            ENTITY_PLAYER = Class.forName(NET_MINECRAFT_SERVER + ".EntityPlayer");
            ENTITY_PLAYER_PING = ENTITY_PLAYER.getField("ping");
            ENTITY_PLAYER_PLAYER_CONNECTION = ENTITY_PLAYER.getField("playerConnection");

            CRAFT_PLAYER = Class.forName(ORG_BUKKIT_CRAFTBUKKIT + ".entity.CraftPlayer");
            CRAFT_PLAYER_GET_HANDLE = CRAFT_PLAYER.getMethod("getHandle");

            PLAYER_LIST = Class.forName(NET_MINECRAFT_SERVER + ".PlayerList");
            PLAYER_LIST_INSTANCE = MINECRAFT_SERVER.getMethod("getPlayerList").invoke(MINECRAFT_SERVER_INSTANCE);
            PLAYER_LIST_MOVE_TO_WORLD = PLAYER_LIST.getMethod("moveToWorld", ENTITY_PLAYER, int.class, boolean.class);

            PLAYER_CONNECTION = Class.forName(NET_MINECRAFT_SERVER + ".PlayerConnection");
            PLAYER_CONNECTION_SEND_PACKET = PLAYER_CONNECTION.getMethod("sendPacket", Class.forName(NET_MINECRAFT_SERVER + ".Packet"));

            I_CHAT_BASE_COMPONENT = Class.forName(NET_MINECRAFT_SERVER + ".IChatBaseComponent");

            CHAT_MESSAGE_TYPE = Class.forName(NET_MINECRAFT_SERVER + ".ChatMessageType");
            CHAT_MESSAGE_TYPE_CHAT = CHAT_MESSAGE_TYPE.getField("CHAT");
            CHAT_MESSAGE_TYPE_GAME_INFO = CHAT_MESSAGE_TYPE.getField("GAME_INFO");
            CHAT_MESSAGE_TYPE_SYSTEM = CHAT_MESSAGE_TYPE.getField("SYSTEM");

            CHAT_SERIALIZER = Class.forName(NET_MINECRAFT_SERVER + ".ChatSerializer");
            CHAT_SERIALIZER_A = CHAT_SERIALIZER.getMethod("a", String.class);

            PACKET_PLAY_OUT_CHAT = Class.forName(NET_MINECRAFT_SERVER + ".PacketPlayOutChat");
            PACKET_PLAY_OUT_CHAT_CONSTRUCTOR = PACKET_PLAY_OUT_CHAT.getConstructor(I_CHAT_BASE_COMPONENT, CHAT_MESSAGE_TYPE);

        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

}
