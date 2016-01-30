package io.github.dre2n.commons.util;

import org.bukkit.entity.EntityType;

public class MobUtil {
	
	@SuppressWarnings("deprecation")
	public static int getId(String string) {
		if (NumberUtil.parseInt(string) > 0) {
			return NumberUtil.parseInt(string);
			
		} else if (EntityType.valueOf(string) != null) {
			return EntityType.valueOf(string).getTypeId();
			
		} else {
			return 50;
		}
	}
	
}
