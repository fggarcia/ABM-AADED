package ar.edu.utn.aadeed.session;

import ar.edu.utn.aadeed.repository.Repository;

public class Session<T> {
	
	private Repository<T> repository;
	
	protected Session(Repository<T> repository) {
		this.repository = repository;
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
}