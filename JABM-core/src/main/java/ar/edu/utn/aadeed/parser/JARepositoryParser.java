package ar.edu.utn.aadeed.parser;

import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.repository.JARepository;
import ar.edu.utn.aadeed.repository.JARepositoryFactory;

public class JARepositoryParser {
	
	public <T> JARepository<T> build(Class<T> clazz) {
		
		JAEntity entity = clazz.getAnnotation(JAEntity.class);
		
		try {
		
			JARepositoryFactory factory = entity.repositoryFactory().newInstance();
			return factory.newInstance(clazz);

		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate repository through factory %s for class %s", entity.repositoryFactory().getName(), clazz.getName());
			throw new RuntimeException(errorMsg);
		}
	}
}