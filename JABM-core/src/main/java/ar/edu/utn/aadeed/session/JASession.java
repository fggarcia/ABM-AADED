package ar.edu.utn.aadeed.session;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.builder.JAViewSessionBuilder;
import ar.edu.utn.aadeed.repository.JARepository;

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
	
	public Class<T> getRepresentationFor() {
		return representationFor;
	}
	
	public JAViewSessionBuilder<T> getViewSessionBuilder() {
		return new JAViewSessionBuilder<T>().withFields(fields).withRepresentationFor(representationFor);
	}

	public JAFiltersBuilder<T> getFiltersBuilder() {
		return new JAFiltersBuilder<T>(fields, repository);
	}
}