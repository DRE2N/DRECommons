package de.erethon.commons.loading;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * A simple class to load and unload user classes.
 * It registers itself as an {@link Listener} to load and unload users through join and quit events.
 *
 * @param <USER> The user object to load
 * @author Fyreum
 */
public abstract class UserCacheLoader<USER extends LoadableUser> implements Listener {

    private final Map<String, UUID> nameToId;
    private final Map<UUID, USER> idToUser;

    /**
     * @param plugin the plugin to register the listener with
     */
    public UserCacheLoader(JavaPlugin plugin) {
        this.nameToId = new HashMap<>();
        this.idToUser = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Loads the user object for every player that is online.
     */
    public void loadAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            load(player);
        }
    }

    /**
     * Loads the user object for the given player and returns the loaded user.
     *
     * @param player the player to load
     * @return the loaded user
     */
    public USER load(Player player) {
        USER user = getNewInstance(player);
        if (user == null) {
            throw new NullPointerException("The user instance for " + player.getName() + " is null -> getNewInstance(OfflinePlayer) has to return a NotNull instance for online players");
        }
        UUID uuid = player.getUniqueId();
        String name = player.getName();

        nameToId.put(name, uuid);
        idToUser.put(uuid, user);
        return user;
    }

    /**
     * Unloads every player that is online.
     */
    public void unloadAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            unload(player);
        }
    }

    /**
     * Unloads the given player and call the {@link LoadableUser#saveUser()} method and returns the unloaded user.
     *
     * @param player the player to unload
     * @return the unloaded user
     */
    public USER unload(Player player) {
        USER user = idToUser.get(player.getUniqueId());
        if (user != null) {
            user.saveUser();
        }
        nameToId.remove(player.getName());
        idToUser.remove(player.getUniqueId());
        return user;
    }

    /**
     * Clears all loaded users before loading every online player back again.
     */
    public void reloadAll() {
        unloadAll();
        loadAll();
    }

    /**
     * Calls the {@link LoadableUser#saveUser()} method for every user in the cache.
     */
    public void saveAll() {
        for (USER user : idToUser.values()) {
            user.saveUser();
        }
    }

    /**
     * Returns the cached user matching the name if found.
     * If no user is found it will try to create a new one.
     *
     * @param name the name to get the user for
     * @see UserCacheLoader#getNewInstance(OfflinePlayer)
     */
    public USER getByName(String name) {
        UUID uuid = nameToId.get(name);
        if (uuid == null) {
            uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
        }
        return getByUniqueId(uuid);
    }

    /**
     * Returns the cached user matching the uuid if found.
     * If no user is found it will try to create a new one.
     *
     * @param uuid the uuid to get the user for
     * @see UserCacheLoader#getNewInstance(OfflinePlayer)
     */
    public USER getByUniqueId(UUID uuid) {
        USER user = idToUser.get(uuid);
        if (user != null) {
            return user;
        }
        return getNewInstance(Bukkit.getOfflinePlayer(uuid));
    }

    /**
     * Returns the cached user matching the player if found.
     * If no user is found it will try to create a new one.
     * <br>
     * <b>Note:</b> online players should always be not null.
     *
     * @param player the player to get the user for
     * @see UserCacheLoader#getNewInstance(OfflinePlayer)
     */
    public USER getByPlayer(OfflinePlayer player) {
        USER user = idToUser.get(player.getUniqueId());
        if (user != null) {
            return user;
        }
        return getNewInstance(player);
    }

    /**
     * Returns a {@link Set} of all loaded users.
     *
     * @return a Set of all loaded users
     */
    public Set<USER> getCachedUsers() {
        return new HashSet<>(idToUser.values());
    }

    /**
     * Returns the amount of users that are currently in the cache.
     *
     * @return the amount of users currently cached
     */
    public int getCachedUsersAmount() {
        return idToUser.size();
    }

    /* abstracts */

    /**
     * This method tries to create a new user instance for the given player.
     * This method can return null if the given {@link OfflinePlayer} never played before or isn't online.
     * <br>
     * <b>Note:</b> online players should always be not null.
     *
     * @param player the player to get the user for
     * @return a new user object if possible, else null
     */
    protected abstract USER getNewInstance(OfflinePlayer player);

    /* listener */

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        USER user = idToUser.get(player.getUniqueId());
        if (user != null) {
            user.updatePlayer(player);
            user.onJoin(event);
            return;
        }
        load(player).onJoin(event);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        USER user = getByPlayer(player);
        if (user != null) {
            user.onQuit(event);
        }
        unload(player);
    }
}
