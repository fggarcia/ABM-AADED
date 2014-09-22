package ar.edu.utn.aadeed.session;

import java.util.List;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.repository.JARepository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class JASession<T> {

	private JARepository<T> repository;

	private List<JAFieldDescription> fieldDescriptions = Lists.newArrayList();
	
	public JASession(JARepository<T> repository, List<JAFieldDescription> fields) {
		this.repository = repository;
		this.fieldDescriptions = fields;
	}

	public boolean add(T newObject) {
		return this.repository.add(newObject);
	}

	public boolean remove(T oldObject) {
		return this.repository.remove(oldObject);
	}

	public boolean update(T oldObject, T newObject) {
		return this.repository.update(oldObject, newObject);
	}

	public int release() {
		return this.repository.release();
	}

	public JAFiltersBuilder<T> getFiltersBuilder() {
		return new JAFiltersBuilder<T>(Lists.newArrayList(findAvailableFilters()), repository);
	}
	
	public List<JAFieldDescription> getFieldDescriptions() {
		return fieldDescriptions;
	}

	private Iterable<JAFieldDescription> findAvailableFilters() {
		return Iterables.filter(fieldDescriptions, new Predicate<JAFieldDescription>() {
			public boolean apply(JAFieldDescription input) {
				return input.isFilter();
			}
		});
	}
}