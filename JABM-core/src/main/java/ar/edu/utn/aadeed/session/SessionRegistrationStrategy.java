package ar.edu.utn.aadeed.session;

import java.lang.reflect.Method;
import java.util.List;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.annotation.Trigger;
import ar.edu.utn.aadeed.annotation.Triggers;
import ar.edu.utn.aadeed.event.TriggerComponent;
import ar.edu.utn.aadeed.repository.Repository;
import ar.edu.utn.aadeed.repository.RepositoryFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

public class SessionRegistrationStrategy {

	public <T> Session<T> buildSession(Class<T> clazz) {

		checkEntityAnnotationPresence(clazz);

		Repository<T> repository = buildRepository(clazz);
		List<TriggerComponent> triggers = retrieveArrayOfTriggers(clazz);

		return new Session<T>(repository, triggers);
	}

	private <T> List<TriggerComponent> retrieveArrayOfTriggers(Class<T> clazz) {

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
		
		Class<?> event = trigger.event();
		checkEvent(event);
		
		TriggerComponent result = null;
		try {
			
			result = new TriggerComponent(event.newInstance(), trigger.moment(), trigger.operation());
			
		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate event %s", event.getName());
			throw new RuntimeException(errorMsg);
		}
		
		return result;
	}
	
	private void checkEvent(Class<?> event) {
		
		boolean anySubscribe = Iterables.any(Lists.newArrayList(event.getMethods()), new Predicate<Method>() {
			public boolean apply(Method method) {
				return method.isAnnotationPresent(Subscribe.class);
			}
		});
		
		if(!anySubscribe) {
			String errorMsg = String.format("Trigger class %s must contain at least one method annotated with Subscribe", event.getName());
			throw new IllegalArgumentException(errorMsg);			
		}
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
			throw new IllegalArgumentException(errorMsg);
		}
	}
}