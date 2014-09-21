package ar.edu.utn.aadeed.repository.memory;

import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.repository.RepositoryFactory;

public class MemoryRepositoryFactory implements RepositoryFactory {

	public <T> Repository<T> newInstance(Class<T> clazz) {
		return new BasicMemoryRepository<T>();
	}
}
