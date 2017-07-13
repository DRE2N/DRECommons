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
package io.github.dre2n.commons.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class PlayerCollection {

    private Set<UUID> uuids = new HashSet<>();

    /**
     * Creates an empty PlayerCollection
     */
    public PlayerCollection() {
    }

    /**
     * @param players
     * a collection of Player, OfflinePlayer, UUID, String (player names), String (uuids) and PlayerWrapper objects
     */
    public PlayerCollection(Collection players) {
        for (Object player : players) {
            if (player instanceof OfflinePlayer) {
                uuids.add(((OfflinePlayer) player).getUniqueId());
            } else if (player instanceof UUID) {
                uuids.add((UUID) player);
            } else if (player instanceof String) {
                if (PlayerUtil.isValidUUID((String) player)) {
                    uuids.add(UUID.fromString((String) player));
                } else {
                    uuids.add(PlayerUtil.getUniqueIdFromName((String) player));
                }
            } else if (player instanceof PlayerWrapper) {
                uuids.add(((PlayerWrapper) player).getUniqueId());
            }
        }
    }

    /**
     * @return
     * a collection of UUIDs
     */
    public Collection<UUID> getUniqueIds() {
        return uuids;
    }

    /**
     * @param filter
     * players to exclude
     * @return
     * a collection of UUIDs
     */
    public Collection<UUID> getUniqueIds(PlayerCollection filter) {
        Set<UUID> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            if (!filter.contains(uuid)) {
                filtered.add(uuid);
            }
        }
        return filtered;
    }

    /**
     * @return
     * a collection of player name Strings
     */
    public Collection<String> getNames() {
        Set<String> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            filtered.add(Bukkit.getOfflinePlayer(uuid).getName());
        }
        return filtered;
    }

    /**
     * @param filter
     * players to exclude
     * @return
     * a collection of player name Strings
     */
    public Collection<String> getNames(PlayerCollection filter) {
        Set<String> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            String name = Bukkit.getOfflinePlayer(uuid).getName();
            if (!filter.contains(uuid)) {
                filtered.add(name);
            }
        }
        return filtered;
    }

    /**
     * @return
     * a collection of OnlinePlayers
     */
    public Collection<Player> getOnlinePlayers() {
        Set<Player> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                filtered.add(player);
            }
        }
        return filtered;
    }

    /**
     * @param filter
     * players to exclude
     * @return
     * a collection of OnlinePlayers
     */
    public Collection<Player> getOnlinePlayers(PlayerCollection filter) {
        Set<Player> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && !filter.contains(player)) {
                filtered.add(player);
            }
        }
        return filtered;
    }

    /**
     * @return
     * a collection of OfflinePlayers
     */
    public Collection<OfflinePlayer> getOfflinePlayers() {
        Set<OfflinePlayer> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            filtered.add(Bukkit.getOfflinePlayer(uuid));
        }
        return filtered;
    }

    /**
     * @param filter
     * players to exclude
     * @return
     * a collection of OfflinePlayers
     */
    public Collection<OfflinePlayer> getOfflinePlayers(PlayerCollection filter) {
        Set<OfflinePlayer> filtered = new HashSet<>();
        for (UUID uuid : uuids) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            if (player != null && !filter.contains(player)) {
                filtered.add(player);
            }
        }
        return filtered;
    }

    /**
     * @return
     * if the collection contains the player
     */
    public boolean contains(Object player) {
        if (player instanceof Collection) {
            for (Object object : (Collection) player) {
                remove(object);
            }
            return true;
        } else if (player instanceof OfflinePlayer) {
            return uuids.contains(((OfflinePlayer) player).getUniqueId());
        } else if (player instanceof UUID) {
            return uuids.contains((UUID) player);
        } else if (player instanceof String) {
            if (PlayerUtil.isValidUUID((String) player)) {
                return uuids.contains(UUID.fromString((String) player));
            } else {
                return uuids.contains(PlayerUtil.getUniqueIdFromName((String) player));
            }
        } else if (player instanceof PlayerWrapper) {
            return uuids.contains(((PlayerWrapper) player).getUniqueId());
        } else {
            return false;
        }
    }

    public boolean add(Object player) {
        if (player instanceof Collection) {
            for (Object object : (Collection) player) {
                add(object);
            }
            return true;
        } else if (player instanceof OfflinePlayer) {
            return uuids.add(((OfflinePlayer) player).getUniqueId());
        } else if (player instanceof UUID) {
            return uuids.add((UUID) player);
        } else if (player instanceof String) {
            if (PlayerUtil.isValidUUID((String) player)) {
                return uuids.add(UUID.fromString((String) player));
            } else {
                return uuids.add(PlayerUtil.getUniqueIdFromName((String) player));
            }
        } else if (player instanceof PlayerWrapper) {
            return uuids.add(((PlayerWrapper) player).getUniqueId());
        } else {
            return false;
        }
    }

    public void addAll(Collection players) {
        for (Object player : players) {
            add(player);
        }
    }

    public void addAll(PlayerCollection players) {
        uuids.addAll(players.uuids);
    }

    public boolean remove(Object player) {
        if (player instanceof OfflinePlayer) {
            return uuids.remove(((OfflinePlayer) player).getUniqueId());
        } else if (player instanceof UUID) {
            return uuids.remove((UUID) player);
        } else if (player instanceof String) {
            if (PlayerUtil.isValidUUID((String) player)) {
                return uuids.remove(UUID.fromString((String) player));
            } else {
                return uuids.remove(PlayerUtil.getUniqueIdFromName((String) player));
            }
        } else if (player instanceof PlayerWrapper) {
            return uuids.remove(((PlayerWrapper) player).getUniqueId());
        } else {
            return false;
        }
    }

    public void removeAll(Collection players) {
        for (Object player : players) {
            remove(player);
        }
    }

    public void removeAll(PlayerCollection players) {
        uuids.removeAll(players.uuids);
    }

    public void clear() {
        uuids.clear();
    }

    public int size() {
        return uuids.size();
    }

    /**
     * @return
     * a List of Strings that can easily be used in a config
     */
    public List<String> serialize() {
        List<String> filtered = new ArrayList<>();
        for (UUID uuid : uuids) {
            filtered.add(uuid.toString());
        }
        return filtered;
    }

}
