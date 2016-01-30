package io.github.dre2n.commons.compatibility;

import io.github.dre2n.commons.config.CoreConfig;

import java.util.ArrayList;
import java.util.List;

public class CompatibilityHandler {
	
	CoreConfig config;
	
	private Internals internals;
	private boolean spigot;
	
	public CompatibilityHandler(CoreConfig config) {
		this.config = config;
		
		if (Package.getPackage("net.minecraft.server.v1_9_R1") != null) {
			internals = Internals.v1_9_R1;
			
		} else if (Package.getPackage("net.minecraft.server.v1_8_R3") != null) {
			internals = Internals.v1_8_R3;
			
		} else if (Package.getPackage("net.minecraft.server.v1_8_R2") != null) {
			internals = Internals.v1_8_R2;
			
		} else if (Package.getPackage("net.minecraft.server.v1_8_R1") != null) {
			internals = Internals.v1_8_R1;
			
		} else if (Package.getPackage("net.minecraft.server.v1_7_R4") != null) {
			internals = Internals.v1_7_R4;
			
		} else if (Package.getPackage("net.minecraft.server.v1_7_R3") != null) {
			internals = Internals.v1_7_R3;
			
		} else if (Package.getPackage("net.minecraft.server.v1_7_R2") != null) {
			internals = Internals.v1_7_R2;
			
		} else if (Package.getPackage("net.minecraft.server.v1_7_R1") != null) {
			internals = Internals.v1_7_R1;
			
		} else {
			for (Package internal : Package.getPackages()) {
				if (internal.getName().matches("net.minecraft.server.v1_[4-6]_.*")) {
					internals = Internals.OUTDATED;
				}
			}
		}
		
		if (internals == null) {
			internals = Internals.UNKNOWN;
		}
		
		if (Package.getPackage("org.spigotmc") != null) {
			spigot = true;
			
		} else {
			spigot = false;
		}
	}
	
	public Internals getInternals() {
		if (config != null) {
			if ( !config.useCompatibilityInternals()) {
				return Internals.UNKNOWN;
			}
		}
		
		return internals;
	}
	
	/**
	 * @return if the server software implements the Spigot API
	 */
	public boolean isSpigot() {
		return spigot;
	}
	
	public static List<Internals> andHigher(Internals internals) {
		List<Internals> andHigher = new ArrayList<Internals>();
		
		switch (internals) {
			case v1_7_R1:
				andHigher.add(Internals.v1_7_R1);
			case v1_7_R2:
				andHigher.add(Internals.v1_7_R2);
			case v1_7_R3:
				andHigher.add(Internals.v1_7_R3);
			case v1_7_R4:
				andHigher.add(Internals.v1_7_R4);
			case v1_8_R1:
				andHigher.add(Internals.v1_8_R1);
			case v1_8_R2:
				andHigher.add(Internals.v1_8_R2);
			case v1_8_R3:
				andHigher.add(Internals.v1_8_R3);
			case v1_9_R1:
				andHigher.add(Internals.v1_9_R1);
			default:
				break;
		}
		
		return andHigher;
	}
	
}
