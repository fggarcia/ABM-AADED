package ar.edu.utn.aadeed;

import java.lang.reflect.Field;

import ar.edu.utn.aadeed.exception.JARuntimeException;

public final class JAReflections {

	public static Object getFieldValue(Object item, String fieldName) {
		
		Class<?> clazz = item.getClass();
		try {
		
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(item);

		} catch (Exception e) {
			String errorMsg = String.format("Could not get %s value", fieldName);
			throw new JARuntimeException(errorMsg, e);
		}
	}
	
	public static void setFieldValue(Object item, String fieldName, Object value) {
		
		Class<?> clazz = item.getClass();
		try {
		
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(item, value);

		} catch (Exception e) {
			String errorMsg = String.format("Could not set %s value", fieldName);
			throw new JARuntimeException(errorMsg, e);
		}
	}
}