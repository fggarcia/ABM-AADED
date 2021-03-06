package ar.edu.utn.aadeed.repository.memory;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAFilter;
import ar.edu.utn.aadeed.repository.JAFilteringStrategy;
import ar.edu.utn.aadeed.repository.JAFilteringStrategy.JAEqualStrategy;
import ar.edu.utn.aadeed.repository.JARepository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class JABasicMemoryRepository<T> implements JARepository<T> {

	private static final Logger Log = LoggerFactory.getLogger(JABasicMemoryRepository.class);
	
	private Set<T> livingObjects = Sets.newConcurrentHashSet();
	
	public boolean add(T newObject) {
		Log.info(String.format("Adding: %s", newObject));
		return livingObjects.add(newObject);
	}

	public boolean remove(T oldObject) {
		Log.info(String.format("Removing: %s", oldObject));
		return livingObjects.remove(oldObject);
	}

	public boolean update(T oldObject, T newObject) {
		Log.info(String.format("Updating: %s With: %s", oldObject, newObject));
		return (remove(oldObject)) ? add(newObject) : false;
	}

	public List<T> search(final List<JAFilter> filters) {
		return Lists.newArrayList(Iterables.filter(livingObjects, new Predicate<T>() {
			public boolean apply(T object) {
				return filter(object, filters);
			}
		}));
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

	private boolean filter(T item, JAFilter filter) {
		
		JAFieldDescription field = filter.getField();

		JAEqualStrategy strategy = JAFilteringStrategy.findStrategy(field.getClazz());
		
		return strategy.doEquals(JAReflections.getFieldValue(item, field.getName()), filter.getValue());
	}
}