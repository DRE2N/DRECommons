package io.github.dre2n.commons.util.itemutil;

import io.github.dre2n.commons.compatibility.CompatibilityHandler;
import io.github.dre2n.commons.javaplugin.CorePlugin;
import io.github.dre2n.commons.util.NumberUtil;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
	
	static CompatibilityHandler compat = CorePlugin.getPlugin().getCompatibilityHandler();
	
	public enum Slot {
		mainhand,
		offhand,
		head,
		torso,
		legs,
		feet,
		UNKNOWN
	}
	
	public static ItemStack setAttribute(ItemStack itemStack, String attributeName, double value, Slot slot) {
		switch (compat.getInternals()) {
			case v1_9_R1:
				return v1_9_R1.setAttribute(itemStack, attributeName, value, slot);
			case v1_8_R3:
				return v1_8_R3.setAttribute(itemStack, attributeName, value);
			case v1_8_R2:
				return v1_8_R2.setAttribute(itemStack, attributeName, value);
			case v1_8_R1:
				return v1_8_R1.setAttribute(itemStack, attributeName, value);
			case v1_7_R4:
				return v1_7_R4.setAttribute(itemStack, attributeName, value);
			case v1_7_R3:
				return v1_7_R3.setAttribute(itemStack, attributeName, value);
			case v1_7_R2:
				return v1_7_R2.setAttribute(itemStack, attributeName, value);
			case v1_7_R1:
				return v1_7_R1.setAttribute(itemStack, attributeName, value);
			default:
				return itemStack;
		}
	}
	
	public static ItemStack setUnbreakable(ItemStack itemStack) {
		if (compat.isSpigot()) {
			itemStack.getItemMeta().spigot().setUnbreakable(true);
			return itemStack;
		}
		
		switch (compat.getInternals()) {
			case v1_9_R1:
				return v1_9_R1.setUnbreakable(itemStack);
			case v1_8_R3:
				return v1_8_R3.setUnbreakable(itemStack);
			case v1_8_R2:
				return v1_8_R2.setUnbreakable(itemStack);
			case v1_8_R1:
				return v1_8_R1.setUnbreakable(itemStack);
			case v1_7_R4:
				return v1_7_R4.setUnbreakable(itemStack);
			case v1_7_R3:
				return v1_7_R3.setUnbreakable(itemStack);
			case v1_7_R2:
				return v1_7_R2.setUnbreakable(itemStack);
			case v1_7_R1:
				return v1_7_R1.setUnbreakable(itemStack);
			default:
				return itemStack;
		}
	}
	
	public static ItemStack setSkullOwner(ItemStack itemStack, String id, String textureValue) {
		switch (compat.getInternals()) {
			case v1_9_R1:
				return v1_9_R1.setSkullOwner(itemStack, id, textureValue);
			case v1_8_R3:
				return v1_8_R3.setSkullOwner(itemStack, id, textureValue);
			case v1_8_R2:
				return v1_8_R2.setSkullOwner(itemStack, id, textureValue);
			case v1_8_R1:
				return v1_8_R1.setSkullOwner(itemStack, id, textureValue);
			case v1_7_R4:
				return v1_7_R4.setSkullOwner(itemStack, id, textureValue);
			case v1_7_R3:
				return v1_7_R3.setSkullOwner(itemStack, id, textureValue);
			case v1_7_R2:
				return v1_7_R2.setSkullOwner(itemStack, id, textureValue);
			case v1_7_R1:
				return v1_7_R1.setSkullOwner(itemStack, id, textureValue);
			default:
				return itemStack;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static int getId(String string) {
		if (NumberUtil.parseInt(string) > 0) {
			return NumberUtil.parseInt(string);
			
		} else if (Material.getMaterial(string) != null) {
			return Material.getMaterial(string).getId();
			
		} else {
			return 267;
		}
	}
	
}
