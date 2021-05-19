package de.erethon.commons.loading;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class is the base foundation for the {@link UserCacheLoader} class.
 *
 * @author Fyreum
 */
public interface LoadableUser {

    /**
     * This method should overwrite the current player.
     * Its called when a user joins to prevent that a previous loaded user has a null player.
     */
    void updatePlayer(Player player);

    /**
     * This method is called when a player joins.
     */
    default void onJoin(PlayerJoinEvent event) {

    }

    /**
     * This method is called when a player quits.
     */
    default void onQuit(PlayerQuitEvent event) {

    }
}
