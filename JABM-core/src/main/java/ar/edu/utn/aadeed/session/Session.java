package ar.edu.utn.aadeed.session;

import java.util.List;
import java.util.Map;

import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.session.field.FieldDescription;
import ar.edu.utn.aadeed.session.filter.FiltersBuilder;

import com.google.common.collect.Maps;

public class Session<T> {

	private Repository<T> repository;

	private Map<String, FieldDescription> fieldDescriptions = Maps.newHashMap();

	protected Session(Repository<T> repository, List<FieldDescription> fields) {
		this.repository = repository;
		this.setFields(fields);
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
		return new FiltersBuilder<T>(fieldDescriptions.keySet(), repository);
	}

	private void setFields(List<FieldDescription> fields) {
		for (FieldDescription field : fields) {
			fieldDescriptions.put(field.getName(), field);
		}
	}
}