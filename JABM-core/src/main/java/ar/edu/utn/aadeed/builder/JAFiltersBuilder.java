package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

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
		
		filters.add(new JAFilter(fieldName, convertFieldValue(fieldName, value)));
		
		return this;
	}
	
	public boolean isValidField(String name) {
		return availableFieldFilters.containsKey(name);
	}

	public List<T> search() {
		return this.repository.search(filters);
	}

	private void checkFieldName(String fieldName) {
		if (!availableFieldFilters.keySet().contains(fieldName)) {
			throw new IllegalArgumentException(String.format("Field %s is not allowed to be a filter", fieldName));
		}
	}
	
	private Object convertFieldValue(String fieldName, Object value) {
		return ConvertUtils.convert(value, availableFieldFilters.get(fieldName).getClazz());
	}
	
	private void setAvailableFieldFilters(List<JAFieldDescription> fields) {
		for (JAFieldDescription field : fields) {
			availableFieldFilters.put(field.getName(), field);
		}
	}
}