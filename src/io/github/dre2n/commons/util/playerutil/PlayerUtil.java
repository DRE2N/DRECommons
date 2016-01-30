package io.github.dre2n.commons.util.playerutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.javaplugin.CorePlugin;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerUtil {
	
	public static Player getOfflinePlayer(String name, UUID uuid, Location location) {
		CompatibilityHandler compat = CorePlugin.getPlugin().getCompatibilityHandler();
		
		if (compat.getInternals() == Internals.v1_9_R1) {
			return v1_9_R1.getOfflinePlayer(name, uuid, location);
			
		} else if (compat.getInternals() == Internals.v1_8_R3) {
			return v1_8_R3.getOfflinePlayer(name, uuid, location);
			
		} else if (compat.getInternals() == Internals.v1_8_R2) {
			return v1_8_R2.getOfflinePlayer(name, uuid, location);
			
		} else if (compat.getInternals() == Internals.v1_8_R1) {
			return v1_8_R1.getOfflinePlayer(name, uuid, location);
			
		} else if (compat.getInternals() == Internals.v1_7_R4) {
			return v1_7_R4.getOfflinePlayer(name, uuid, location);
			
		} else if (compat.getInternals() == Internals.v1_7_R3) {
			return v1_7_R3.getOfflinePlayer(name, uuid, location);
			
		} else {
			return null;
		}
	}
	
	public static void secureTeleport(Player player, Location location) {
		if (player.isInsideVehicle()) {
			player.leaveVehicle();
		}
		
		player.teleport(location);
	}
	
}
