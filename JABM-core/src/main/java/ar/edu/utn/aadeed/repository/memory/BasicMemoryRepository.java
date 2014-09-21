package ar.edu.utn.aadeed.repository.memory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.Filter;
import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BasicMemoryRepository<T> implements Repository<T> {

	static final Logger Log = LoggerFactory.getLogger(BasicMemoryRepository.class);

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

	public List<T> search(List<Filter> filters) {
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

	private boolean filter(final T object, List<Filter> filters) {
		
		return Iterables.all(filters, new Predicate<Filter>() {
			public boolean apply(Filter filter) {
				return filter(object, filter);
			}
		});
	}

	private boolean filter(T object, Filter filter) {
		
		try {
			
			Field field = object.getClass().getDeclaredField(filter.getFieldName());
			field.setAccessible(true);
			
			return Objects.equals(field.get(object), filter.getValue());
			
		} catch (Exception e) {
			Log.error(String.format("Could not filter field %s in class %s", filter.getFieldName(), object.getClass().getName()));
		}
		
		return false;
	}
}