package ar.edu.utn.aadeed.session;

import java.util.List;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.annotation.Triggers;
import ar.edu.utn.aadeed.annotation.Triggers.Trigger;
import ar.edu.utn.aadeed.event.TriggerComponent;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.repository.RepositoryFactory;

import com.google.common.collect.Lists;

public class SessionRegistrationStrategy {

	public <T> Session<T> buildSession(Class<T> clazz) {

		checkEntityAnnotationPresence(clazz);

		Repository<T> repository = buildRepository(clazz);
		List<TriggerComponent> triggers = retrieveTriggers(clazz);

		return new Session<T>(repository, triggers);
	}

	private <T> List<TriggerComponent> retrieveTriggers(Class<T> clazz) {

		List<TriggerComponent> result = Lists.newArrayList();

		if (clazz.isAnnotationPresent(Triggers.class)) {
			Trigger[] triggers = clazz.getAnnotation(Triggers.class).value();
			for (Trigger trigger : triggers) {
				result.add(buildTriggerComponent(trigger));
			}
		}

		return result;
	}
	
	private TriggerComponent buildTriggerComponent(Trigger trigger) {
		
		TriggerComponent result = null;
		Class<?> observer = trigger.action();
		try {
			
			result = new TriggerComponent(trigger.action().newInstance(), trigger.moment(), trigger.operation());
			
		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate observer %s", observer.getName());
			throw new RuntimeException(errorMsg);
		}
		
		return result;
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
			String errorMsg = String.format("Class %s must be annotated with %s", clazz.getName(), Entity.class.getName());
			throw new RuntimeException(errorMsg);
		}
	}
}