package io.github.dre2n.commons;

import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.javaplugin.BRPluginSettings;
import io.github.dre2n.commons.javaplugin.BRPlugin;

public class BlueRose extends BRPlugin {
	
	public BlueRose() {
		settings = new BRPluginSettings(Internals.INDEPENDENT, false, false, false, false);
	}
	
}
