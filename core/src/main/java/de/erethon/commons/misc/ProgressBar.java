/*
 * Written from 2015-2020 by Daniel Saukel
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
import de.erethon.commons.javaplugin.DREPlugin;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * An action bar based progress bar.
 *
 * @author Daniel Saukel
 */
public class ProgressBar extends BukkitRunnable {

    public static final String BAR = "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588";

    protected Set<UUID> players = new HashSet<>();
    protected int seconds;
    protected int secondsLeft;

    public ProgressBar(Set<Player> players, int seconds) {
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
            cancel();
        } else {
            secondsLeft--;
        }
    }

    /* Statics */
    public static String getBar(int tenths) {
        StringBuilder bar = new StringBuilder(BAR);
        bar.insert(tenths, ChatColor.DARK_RED.toString());
        return ChatColor.GREEN.toString() + bar.toString();
    }

    public static String getBar(double percentage) {
        return getBar((int) Math.round(percentage) / 10);
    }

    /**
     * Send the progress bar to a player.
     *
     * @param player  the player to send the bar to
     * @param seconds the seconds until the progress bar is finished
     * @return the started task
     */
    public static BukkitTask sendProgressBar(Player player, int seconds) {
        return new ProgressBar(player, seconds).runTaskTimer(DREPlugin.getInstance(), 0L, 20L);
    }

    /**
     * Send the progress bar to multiple players
     *
     * @param players the players to send the bar to
     * @param seconds the seconds until the progress bar is finished
     * @return the started task
     */
    public static BukkitTask sendProgressBar(Set<Player> players, int seconds) {
        return new ProgressBar(players, seconds).runTaskTimer(DREPlugin.getInstance(), 0L, 20L);
    }

}
