package io.github.dre2n.commons.javaplugin;

import java.util.HashSet;
import java.util.Set;

import io.github.dre2n.commons.compatibility.Internals;

public class BRPluginSettings {
	
	private Set<Internals> internals = new HashSet<Internals>();
	private boolean uuid;
	private boolean spigot;
	private boolean economy;
	private boolean permissions;
	
	public BRPluginSettings(Set<Internals> internals, boolean spigot, boolean uuid, boolean economy, boolean permissions) {
		this.internals = internals;
		this.spigot = spigot;
		this.uuid = uuid;
		this.economy = economy;
		this.permissions = permissions;
	}
	
	/**
	 * @return the internals supported by this plugin
	 */
	public Set<Internals> getInternals() {
		return internals;
	}
	
	/**
	 * @return if this plugin requires UUID support
	 */
	public boolean requiresUUID() {
		return uuid;
	}
	
	/**
	 * @return if this plugin requires the Spigot API
	 */
	public boolean requiresSpigot() {
		return spigot;
	}
	
	/**
	 * @return if this plugin requires the economy API of Vault
	 */
	public boolean requiresEconomy() {
		return economy;
	}
	
	/**
	 * @return if this plugin requires the permission API of Vault
	 */
	public boolean requiresPermissions() {
		return permissions;
	}
	
}
