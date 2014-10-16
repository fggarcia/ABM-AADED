package ar.edu.utn.aadeed.repository;

import org.apache.commons.lang.StringUtils;

public final class FilteringStrategy {

	public interface EqualStrategy {
		public boolean doEquals(Object first, Object second);
	}

	private static EqualStrategy StringEqualStrategy = new EqualStrategy() {
		public boolean doEquals(Object first, Object second) {
			return StringUtils.containsIgnoreCase(first.toString(), second.toString());
		}
	};

	public static boolean doEquals(Object first, Object second) {
		if (String.class.isInstance(first)) {
			return StringEqualStrategy.doEquals(first, second);
		}
		return false;
	}
}