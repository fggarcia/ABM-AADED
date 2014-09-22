package ar.edu.utn.aadeed.parser;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.repository.RepositoryFactory;

public class RepositoryParser {
	
	public <T> Repository<T> build(Class<T> clazz) {
		
		Entity entity = clazz.getAnnotation(Entity.class);
		
		try {
		
			RepositoryFactory factory = entity.repositoryFactory().newInstance();
			return factory.newInstance(clazz);

		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate repository through factory %s for class %s", entity.repositoryFactory().getName(), clazz.getName());
			throw new RuntimeException(errorMsg);
		}
	}
}