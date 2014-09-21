package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.Filter;
import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FiltersBuilder<T> {

	private Map<String, FieldDescription> availableFieldFilters = Maps.newHashMap();

	private Repository<T> repository;

	private List<Filter> filters = Lists.newArrayList();

	FiltersBuilder(List<FieldDescription> availableFieldFilters, Repository<T> repository) {
		this.repository = repository;
		this.setAvailableFieldFilters(availableFieldFilters);
	}

	public FiltersBuilder<T> add(String fieldName, Object value) {
		
		checkArgument(fieldName != null, "fieldName cannot be null");
		checkArgument(value != null, "value cannot be null");
		
		checkFieldName(fieldName);
		checkFieldValue(fieldName, value.getClass());
		
		filters.add(new Filter(fieldName, value));
		
		return this;
	}

	public List<T> search() {
		return this.repository.search(filters);
	}

	private void checkFieldName(String fieldName) {
		if (!availableFieldFilters.keySet().contains(fieldName)) {
			throw new IllegalArgumentException(String.format("Field %s is not allowed to be a filter", fieldName));
		}
	}
	
	private void checkFieldValue(String fieldName, Class<?> clazz) {
		if (!availableFieldFilters.get(fieldName).getClazz().isAssignableFrom(clazz)) {
			throw new IllegalArgumentException(String.format("The type of field %s is not compatible with %s", fieldName, clazz.getName()));
		}
	}
	
	private void setAvailableFieldFilters(List<FieldDescription> fields) {
		for (FieldDescription field : fields) {
			availableFieldFilters.put(field.getName(), field);
		}
	}
}