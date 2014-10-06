package ar.edu.utn.aadeed.repository.memory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFilter;
import ar.edu.utn.aadeed.repository.JARepository;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class JABasicMemoryRepository<T> implements JARepository<T> {

	static final Logger Log = LoggerFactory.getLogger(JABasicMemoryRepository.class);

	private Set<T> livingObjects = Sets.newConcurrentHashSet();
	
	public boolean add(T newObject) {
		return livingObjects.add(newObject);
	}

	public boolean remove(T oldObject) {
		return livingObjects.remove(oldObject);
	}

	public boolean update(T oldObject, T newObject) {
		return (remove(oldObject)) ? add(newObject) : false;
	}

	public List<T> search(List<JAFilter> filters) {
		List<T> result = Lists.newArrayList();
		for (T object : livingObjects) {
			if (filter(object, filters)) {
				result.add(object);
			}
		}
		return result;
	}

	public int release() {
		int size = livingObjects.size();
		livingObjects.clear();
		return size;
	}

	private boolean filter(final T object, List<JAFilter> filters) {
		
		return Iterables.all(filters, new Predicate<JAFilter>() {
			public boolean apply(JAFilter filter) {
				return filter(object, filter);
			}
		});
	}

	private boolean filter(T object, JAFilter filter) {
		
		try {
			
			Field field = object.getClass().getDeclaredField(filter.getFieldName());
			field.setAccessible(true);
			return doEquals(field.get(object), filter.getValue());
			
		} catch (Exception e) {
			Log.error(String.format("Could not filter field %s in class %s", filter.getFieldName(), object.getClass().getName()));
		}
		
		return false;
	}
	
	//TODO See how we can refactor this...
	public boolean doEquals(Object first, Object second) {
		if (String.class.isInstance(first) && String.class.isInstance(second)) {
			return StringUtils.containsIgnoreCase(first.toString(), second.toString());
		}
		return Objects.equal(first, second);
	}
}