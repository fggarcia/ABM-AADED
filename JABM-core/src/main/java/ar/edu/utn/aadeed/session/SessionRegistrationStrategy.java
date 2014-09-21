package ar.edu.utn.aadeed.session;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.ViewDescription;
import ar.edu.utn.aadeed.repository.Repository;

public class SessionRegistrationStrategy {
	
	static final Logger Log = LoggerFactory.getLogger(SessionRegistrationStrategy.class);
	
	private final RepositoryParser repositoryParser = new RepositoryParser();
	
	private final FieldDescriptionsParser fieldDescriptionsParser = new FieldDescriptionsParser();
	
	private final ViewDescriptionsParser viewDescriptionsParser = new ViewDescriptionsParser();
	
	public <T> Session<T> buildSession(Class<T> clazz) {

		checkEntityAnnotationPresence(clazz);

		Repository<T> repository = repositoryParser.build(clazz);
		List<FieldDescription> fields = fieldDescriptionsParser.build(clazz);
		List<ViewDescription> views = viewDescriptionsParser.build(clazz, fields);

		return new Session<T>(repository, fields, views);
	}

	private <T> void checkEntityAnnotationPresence(Class<T> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			String errorMsg = String.format("Class %s must be annotated with %s", clazz.getName(), Entity.class.getName());
			Log.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
	}
}