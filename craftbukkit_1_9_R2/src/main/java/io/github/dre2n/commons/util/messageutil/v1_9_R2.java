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
package io.github.dre2n.commons.util.messageutil;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import net.minecraft.server.v1_9_R2.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_9_R2.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Daniel Saukel
 */
class v1_9_R2 extends InternalsProvider {

    v1_9_R2(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int show, int fadeOut) {
        subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        title = ChatColor.translateAlternateColorCodes('&', title);

        IChatBaseComponent subtitleComponent = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        IChatBaseComponent titleComponent = ChatSerializer.a("{\"text\": \"" + title + "\"}");

        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleComponent);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleComponent);
        PacketPlayOutTitle timesPacket = new PacketPlayOutTitle(fadeIn, show, fadeOut);

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(subtitlePacket);
        connection.sendPacket(titlePacket);
        connection.sendPacket(timesPacket);
    }

    @Override
    void sendActionBarMessage(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        IChatBaseComponent messageComponent = ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat barPacket = new PacketPlayOutChat(messageComponent, (byte) 2);

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(barPacket);
    }

    @Override
    void sendItemBarMessage(final Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        final PlayerInventory inventory = player.getInventory();
        final int messageSlot = player.getInventory().getHeldItemSlot();
        int alternativeSlot = player.getInventory().getHeldItemSlot() - 1 == -1 ? 8 : player.getInventory().getHeldItemSlot() - 1;
        final ItemStack savedItem = player.getInventory().getItem(messageSlot);
        ItemStack messageItem = new ItemStack(Material.AIR);
        if (savedItem != null) {
            messageItem = savedItem.clone();
        }
        ItemMeta meta = messageItem.getItemMeta();
        meta.setDisplayName(message);
        messageItem.setItemMeta(meta);

        PacketPlayOutHeldItemSlot alternativeSlotPacket = new PacketPlayOutHeldItemSlot(alternativeSlot);
        PacketPlayOutHeldItemSlot messageSlotPacket = new PacketPlayOutHeldItemSlot(messageSlot);

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(alternativeSlotPacket);
        inventory.setItem(messageSlot, messageItem);
        connection.sendPacket(messageSlotPacket);
        new BukkitRunnable() {
            @Override
            public void run() {
                inventory.setItem(messageSlot, savedItem);
            }
        }.runTaskLater(plugin, 40L);
    }

}
