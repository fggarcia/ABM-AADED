package ar.edu.utn.aadeed.session;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.repository.RepositoryFactory;

public class SessionRegistrationStrategy {

	public <T> Session<T> buildSession(Class<T> clazz) {
		
		checkEntityAnnotationPresence(clazz);
		Repository<T> repository = buildRepository(clazz);
		
		return new Session<T>(repository);
	}

	private <T> Repository<T> buildRepository(Class<T> clazz) {

		Repository<T> repository = null;
		Entity entity = clazz.getAnnotation(Entity.class);
		try {
		
			RepositoryFactory factory = entity.repositoryFactory().newInstance();
			repository = factory.newInstance(clazz);
			
		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate repository through factory %s for class %s", entity.repositoryFactory().getName(), clazz.getName());
			throw new RuntimeException(errorMsg);
		}
		
		return repository;
	}

	private <T> void checkEntityAnnotationPresence(Class<T> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			String errorMsg = String.format("Class %s must be annotated with %s", clazz.getName(),Entity.class.getName());
			throw new RuntimeException(errorMsg);
		}
	}
}