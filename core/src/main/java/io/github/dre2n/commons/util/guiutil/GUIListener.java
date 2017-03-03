/*
 * Copyright (C) 2016-2017 Daniel Saukel
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
package io.github.dre2n.commons.util.guiutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.javaplugin.BRPlugin;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author Daniel Saukel
 */
@Deprecated
public class GUIListener implements Listener {

    private BRPlugin plugin;
    private Set<Inventory> guis;

    public GUIListener(BRPlugin plugin) {
        this.plugin = plugin;
        this.guis = plugin.getGUIs();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory gui = event.getInventory();
        int slot = event.getSlot();
        if (!guis.contains(gui) || !(event.getWhoClicked() instanceof Player) || slot > gui.getSize() - 1 || slot == -999 || gui.getItem(slot) == null) {
            return;
        }

        if (CompatibilityHandler.getInstance().isSpigot()) {
            if (!guis.contains(event.getClickedInventory())) {
                return;
            }
        }

        ButtonClickEvent buttonClickEvent = new ButtonClickEvent((Player) event.getWhoClicked(), gui, slot);
        plugin.getServer().getPluginManager().callEvent(buttonClickEvent);
        if (!buttonClickEvent.isCancelled()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (guis.contains(event.getInventory())) {
            plugin.removeGUI(event.getInventory());
        }
    }

}