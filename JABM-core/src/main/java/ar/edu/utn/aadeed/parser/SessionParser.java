package ar.edu.utn.aadeed.parser;

import java.util.List;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.session.Session;

public class SessionParser {
	
	private final RepositoryParser repositoryParser = new RepositoryParser();
	
	private final FieldDescriptionsParser fieldDescriptionsParser = new FieldDescriptionsParser();
	
	public <T> Session<T> buildSession(Class<T> clazz) {

		checkEntityAnnotationPresence(clazz);

		Repository<T> repository = repositoryParser.build(clazz);
		List<FieldDescription> fields = fieldDescriptionsParser.build(clazz);

		return new Session<T>(repository, fields);
	}

	private <T> void checkEntityAnnotationPresence(Class<T> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			String errorMsg = String.format("Class %s must be annotated with %s", clazz.getName(), Entity.class.getName());
			throw new IllegalArgumentException(errorMsg);
		}
	}
}