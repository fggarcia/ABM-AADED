package ar.edu.utn.aadeed.session;

import ar.edu.utn.aadeed.repository.JARepository;
import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.view.builder.JAViewSessionBuilder;

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
	
	public JAFields getFields() {
		return fields;
	}
	
	public JAViewSessionBuilder<T> getViewSessionBuilder() {
		return new JAViewSessionBuilder<T>().withSession(this);
	}

	public JAFiltersBuilder<T> getFiltersBuilder() {
		return new JAFiltersBuilder<T>(fields, repository);
	}
}