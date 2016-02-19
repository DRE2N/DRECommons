package io.github.dre2n.commons.util.itemutil;

public enum Slot {
	
	MAIN_HAND("mainhand"),
	OFF_HAND("offhand"),
	HEAD("head"),
	TORSO("torso"),
	LEGS("legs"),
	FEET("feet");
	
	private String internalName;
	
	Slot(String internalName) {
		this.internalName = internalName;
	}
	
	@Override
	public String toString() {
		return internalName;
	}
	
}
