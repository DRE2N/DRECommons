package io.github.dre2n.commons;

import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.config.BRSettings;
import io.github.dre2n.commons.javaplugin.CorePlugin;

public class BlueRose extends CorePlugin {
	
	public BlueRose() {
		settings = new BRSettings(Internals.INDEPENDENT, false, false, false, false);
	}
	
}
