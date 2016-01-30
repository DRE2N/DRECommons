package io.github.dre2n.commons;

import io.github.dre2n.commons.javaplugin.CorePlugin;

import java.io.File;

public class BlueRose extends CorePlugin {
	
	@Override
	public void onEnable() {
		getDataFolder().mkdir();
		loadCoreConfig(new File(getDataFolder(), "core.yml"));
		super.onEnable();
	}
	
}
