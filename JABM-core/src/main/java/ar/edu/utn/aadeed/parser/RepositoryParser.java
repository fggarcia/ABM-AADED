package ar.edu.utn.aadeed.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.repository.RepositoryFactory;

public class RepositoryParser {
	
	static final Logger Log = LoggerFactory.getLogger(RepositoryParser.class);

	public <T> Repository<T> build(Class<T> clazz) {

		Repository<T> repository = null;
		Entity entity = clazz.getAnnotation(Entity.class);
		try {

			RepositoryFactory factory = entity.repositoryFactory().newInstance();
			repository = factory.newInstance(clazz);

		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate repository through factory %s for class %s", entity.repositoryFactory().getName(), clazz.getName());
			Log.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}

		return repository;
	}
}