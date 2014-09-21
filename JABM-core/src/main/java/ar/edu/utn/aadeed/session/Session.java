package ar.edu.utn.aadeed.session;

import java.util.List;

import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.ViewDescription;
import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Session<T> {

	private Repository<T> repository;

	private List<FieldDescription> fieldDescriptions = Lists.newArrayList();
	
	private List<ViewDescription> viewDescriptions = Lists.newArrayList(); 

	protected Session(Repository<T> repository, List<FieldDescription> fields, List<ViewDescription> views) {
		this.repository = repository;
		this.fieldDescriptions = fields;
		this.viewDescriptions = views;
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

	public FiltersBuilder<T> getFiltersBuilder() {
		return new FiltersBuilder<T>(Lists.newArrayList(findAvailableFilters()), repository);
	}

	private Iterable<FieldDescription> findAvailableFilters() {
		return Iterables.filter(fieldDescriptions, new Predicate<FieldDescription>() {
			public boolean apply(FieldDescription input) {
				return input.isFilter();
			}
		});
	}
}