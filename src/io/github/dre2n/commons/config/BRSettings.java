package io.github.dre2n.commons.config;

import java.util.HashSet;
import java.util.Set;

import io.github.dre2n.commons.compatibility.Internals;

public class BRSettings {
	
	public Set<Internals> internals = new HashSet<Internals>();
	public boolean uuid;
	public boolean spigot;
	public boolean economy;
	public boolean permissions;
	
	public BRSettings(Set<Internals> internals, boolean spigot, boolean uuid, boolean economy, boolean permissions) {
		this.internals = internals;
		this.spigot = spigot;
		this.uuid = uuid;
		this.economy = economy;
		this.permissions = permissions;
	}
	
}
