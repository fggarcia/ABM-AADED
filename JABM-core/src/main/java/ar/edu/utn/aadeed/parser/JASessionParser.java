package ar.edu.utn.aadeed.parser;

import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.repository.JARepository;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.session.JASession;

public class JASessionParser {
	
	private final JARepositoryParser repositoryParser = new JARepositoryParser();
	
	private final JAFieldDescriptionsParser fieldDescriptionsParser = new JAFieldDescriptionsParser();
	
	public <T> JASession<T> buildSession(Class<T> clazz) {

		checkEntityAnnotationPresence(clazz);

		JARepository<T> repository = repositoryParser.build(clazz);
		JAFields fields = fieldDescriptionsParser.build(clazz);

		return new JASession<T>(repository, fields, clazz);
	}

	private <T> void checkEntityAnnotationPresence(Class<T> clazz) {
		if (!clazz.isAnnotationPresent(JAEntity.class)) {
			String errorMsg = String.format("Class %s must be annotated with %s", clazz.getName(), JAEntity.class.getName());
			throw new IllegalArgumentException(errorMsg);
		}
	}
}