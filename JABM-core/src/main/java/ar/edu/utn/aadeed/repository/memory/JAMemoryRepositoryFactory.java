package ar.edu.utn.aadeed.repository.memory;

import ar.edu.utn.aadeed.repository.JARepository;
import ar.edu.utn.aadeed.repository.JARepositoryFactory;

public class JAMemoryRepositoryFactory implements JARepositoryFactory {

	public <T> JARepository<T> newInstance(Class<T> clazz) {
		return new JABasicMemoryRepository<T>();
	}
}
