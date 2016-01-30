package io.github.dre2n.commons.compatibility;

public enum Internals {
	
	v1_9_R1(true),
	v1_8_R3(true),
	v1_8_R2(true),
	v1_8_R1(true),
	v1_7_R4(true),
	v1_7_R3(true),
	v1_7_R2(false),
	v1_7_R1(false),
	OUTDATED(false),
	UNKNOWN(true);
	
	private boolean uuids;
	
	Internals(boolean uuids) {
		this.uuids = uuids;
	}
	
	public boolean useUUIDs() {
		return uuids;
	}
	
}
