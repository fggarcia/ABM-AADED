package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAFilter;
import ar.edu.utn.aadeed.repository.JARepository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JAFiltersBuilder<T> {

	private Map<String, JAFieldDescription> availableFieldFilters = Maps.newHashMap();

	private JARepository<T> repository;

	private List<JAFilter> filters = Lists.newArrayList();

	public JAFiltersBuilder(List<JAFieldDescription> availableFieldFilters, JARepository<T> repository) {
		this.repository = repository;
		this.setAvailableFieldFilters(availableFieldFilters);
	}

	public JAFiltersBuilder<T> add(String fieldName, Object value) {
		
		checkArgument(fieldName != null, "fieldName cannot be null");
		checkArgument(value != null, "value cannot be null");
		
		checkFieldName(fieldName);
		checkFieldValue(fieldName, value.getClass());
		
		filters.add(new JAFilter(fieldName, value));
		
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
	
	private void setAvailableFieldFilters(List<JAFieldDescription> fields) {
		for (JAFieldDescription field : fields) {
			availableFieldFilters.put(field.getName(), field);
		}
	}
}