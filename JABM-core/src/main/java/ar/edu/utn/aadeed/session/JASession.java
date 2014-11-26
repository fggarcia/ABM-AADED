package ar.edu.utn.aadeed.session;

import java.util.Map;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.repository.JARepository;
import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;

public class JASession<T> {

	private JARepository<T> repository;
	
	private JAFields fields;

	private Class<T> representationFor;
	
	public JASession(JARepository<T> repository, JAFields fields, Class<T> representationFor) {
		this.repository = repository;
		this.fields = fields;
		this.representationFor = representationFor;
	}

	public boolean add(T newObject) {
		
		fields.validateInput(newObject);
		
		return this.repository.add(newObject);
	}

	public boolean remove(T oldObject) {
		
		return this.repository.remove(oldObject);
	}

	public boolean update(T oldObject, T newObject) {
		
		fields.validateInput(oldObject, newObject);
		
		return this.repository.update(oldObject, newObject);
	}

	public int release() {
		return this.repository.release();
	}
	
	public T createItem(Map<String, Object> values) {
		try {
			
			T instance = representationFor.newInstance();
			
			for (String fieldName : values.keySet()) {
				JAReflections.setFieldValue(instance, fieldName, values.get(fieldName));
			}
			
			return instance;
		
		} catch (Exception e) {
			throw new JARuntimeException("Cannot create item", e);
		}
	}
	
	public Class<T> getRepresentationFor() {
		return representationFor;
	}
	
	public JAFields getFields() {
		return fields;
	}
	
	public JAFiltersBuilder<T> getFiltersBuilder() {
		return new JAFiltersBuilder<T>(fields, repository);
	}
}