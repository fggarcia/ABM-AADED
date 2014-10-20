package ar.edu.utn.aadeed.repository;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

public final class JAFilteringStrategy {

	public interface JAEqualStrategy {
		public boolean doEquals(Object memory, Object input);
	}
	
	private final static JAEqualStrategy DefaultStrategy = new JAEqualStrategy() {
		public boolean doEquals(Object memory, Object input) {
			return Objects.equal(memory, input);
		}
	};
	
	private final static Map<Class<?>, JAEqualStrategy> Strategies = Maps.newHashMap();
	
	static {
		
		Strategies.put(String.class, new JAEqualStrategy() {
			public boolean doEquals(Object memory, Object input) {
				return StringUtils.containsIgnoreCase(memory.toString(), input.toString());
			}
		});
	}

	public static JAEqualStrategy findStrategy(Class<?> clazz) {
		return (Strategies.containsKey(clazz)) ? Strategies.get(clazz) : DefaultStrategy;
	}
}