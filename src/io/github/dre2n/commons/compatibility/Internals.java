package io.github.dre2n.commons.compatibility;

import java.util.HashSet;
import java.util.Set;

public enum Internals {
	
	v1_9_R1,
	v1_8_R3,
	v1_8_R2,
	v1_8_R1,
	v1_7_R4,
	v1_7_R3,
	v1_7_R2,
	v1_7_R1,
	GLOWSTONE,
	OUTDATED,
	UNKNOWN;
	
	// Statics
	
	public static final Set<Internals> INDEPENDENT;
	
	static {
		INDEPENDENT = andHigher(v1_7_R1);
		INDEPENDENT.add(GLOWSTONE);
		INDEPENDENT.add(OUTDATED);
		INDEPENDENT.add(UNKNOWN);
	}
	
	/**
	 * @param internals
	 * the oldest internals in the list
	 */
	public static Set<Internals> andHigher(Internals internals) {
		Set<Internals> andHigher = new HashSet<Internals>();
		
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
