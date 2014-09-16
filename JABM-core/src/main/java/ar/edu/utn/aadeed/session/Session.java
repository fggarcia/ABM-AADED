package ar.edu.utn.aadeed.session;

import ar.edu.utn.aadeed.repository.Repository;

public class Session<T> {

	private Repository<T> repository;

	protected Session(Repository<T> repository) {
		this.repository = repository;
	}

	public boolean add(T object) {
		return this.repository.add(object);
	}

	public boolean remove(T object) {
		return this.repository.remove(object);
	}
}