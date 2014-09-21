package ar.edu.utn.aadeed.session;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.session.field.FieldDescription;
import ar.edu.utn.aadeed.session.field.FieldsBuilder;

public class SessionRegistrationStrategy {
	
	static final Logger Log = LoggerFactory.getLogger(SessionRegistrationStrategy.class);
	
	private final RepositoryBuilder repositoryBuilder = new RepositoryBuilder();
	
	private final FieldsBuilder fieldsBuilder = new FieldsBuilder();
	
	public <T> Session<T> buildSession(Class<T> clazz) {

		checkEntityAnnotationPresence(clazz);

		Repository<T> repository = repositoryBuilder.build(clazz);
		List<FieldDescription> fields = fieldsBuilder.build(clazz);

		return new Session<T>(repository, fields);
	}

	private <T> void checkEntityAnnotationPresence(Class<T> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			String errorMsg = String.format("Class %s must be annotated with %s", clazz.getName(), Entity.class.getName());
			Log.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
	}
}