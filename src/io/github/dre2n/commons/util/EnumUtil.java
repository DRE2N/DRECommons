package io.github.dre2n.commons.util;

public class EnumUtil {
	
	/**
	 * @param enumClass
	 * the enum
	 * @param enumName
	 * the name of the enum value
	 * @return if the enum value with this name is valid
	 */
	public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String valueName) {
		if (enumClass == null || valueName == null) {
			return false;
		}
		
		try {
			Enum.valueOf(enumClass, valueName);
			return true;
			
		} catch (IllegalArgumentException exception) {
			return false;
		}
	}
	
}
