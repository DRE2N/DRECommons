/*
 * Written from 2015-2021 by Daniel Saukel
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

import de.erethon.commons.chat.MessageUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * A boss bar based progress bar.
 *
 * @author Daniel Saukel
 */
public class ProgressBar extends BukkitRunnable {

    public static final String BAR = "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588";

    private List<UUID> players = new ArrayList<>();
    private int seconds;
    private int secondsLeft;

    public ProgressBar(Collection<Player> players, int seconds) {
        for (Player player : players) {
            this.players.add(player.getUniqueId());
        }
        this.seconds = seconds;
        this.secondsLeft = seconds;
    }

    public ProgressBar(Player player, int seconds) {
        this.players.add(player.getUniqueId());
        this.seconds = seconds;
        this.secondsLeft = seconds;
    }

    /**
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
    }

    /**
     * @param player the player to remove
     */
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    @Override
    public void run() {
        int i = (int) Math.round(((double) secondsLeft / (double) seconds) * 10);
        StringBuilder bar = new StringBuilder(BAR);
        bar.insert(10 - i, ChatColor.DARK_RED.toString());
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                MessageUtil.sendActionBarMessage(player, ChatColor.GREEN.toString() + bar.toString());
            }
        }

        if (secondsLeft == 0) {
            onFinish();
            cancel();
        } else {
            secondsLeft--;
        }
    }

    /**
     * Method to override to set actions when no seconds are left.
     */
    public void onFinish() {
    }

    /**
     * Sends the progress bar to a player
     *
     * @param plugin the plugin instance
     * @return the scheduled BukkitTask
     */
    public BukkitTask send(Plugin plugin) {
        return runTaskTimer(plugin, 0L, 20L);
    }

}
