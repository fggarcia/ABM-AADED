package ar.edu.utn.aadeed.session.filter;

import java.util.List;
import java.util.Set;

import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.collect.Lists;

public class FiltersBuilder<T> {

	private Set<String> availableFilters;

	private Repository<T> repository;

	private List<Filter> filters = Lists.newArrayList();

	public FiltersBuilder(Set<String> availableFilters, Repository<T> repository) {
		this.availableFilters = availableFilters;
		this.repository = repository;
	}

	public FiltersBuilder<T> add(String fieldName, Object value) {
		checkFieldName(fieldName);
		filters.add(new Filter(fieldName, value));
		return this;
	}

	public List<T> search() {
		return this.repository.search(filters);
	}

	private void checkFieldName(String fieldName) {
		if (!availableFilters.contains(fieldName)) {
			throw new IllegalArgumentException(String.format("FieldName %s is not allow to be a filter", fieldName));
		}
	}
}