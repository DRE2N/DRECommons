package io.github.dre2n.commons.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorUtil {
	
	public static Vector getOrthogonal(Location location, Vector vector) {
		Vector orthogonal = new Vector(location.getX(), 0, 1);
		return orthogonal.setZ( -1 * vector.dot(orthogonal));
	}
	
}
