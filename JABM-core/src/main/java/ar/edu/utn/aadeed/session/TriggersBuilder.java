package ar.edu.utn.aadeed.session;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.Trigger;
import ar.edu.utn.aadeed.annotation.Triggers;
import ar.edu.utn.aadeed.event.TriggerComponent;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

public class TriggersBuilder {
	
	static final Logger Log = LoggerFactory.getLogger(TriggersBuilder.class);
	
	public <T> List<TriggerComponent> build(Class<T> clazz) {
		
		List<TriggerComponent> triggers = Lists.newArrayList();
		addArrayOfTriggers(clazz, triggers);
		addSingleTrigger(clazz, triggers);
		
		return triggers;
	}
	
	private <T> void addSingleTrigger(Class<T> clazz, List<TriggerComponent> triggerComponents) {
		if (clazz.isAnnotationPresent(Trigger.class)) {
			Trigger trigger = clazz.getAnnotation(Trigger.class);
			triggerComponents.add(buildTriggerComponent(trigger));
		}
	}
	
	private <T> void addArrayOfTriggers(Class<T> clazz, List<TriggerComponent> triggerComponents) {
		if (clazz.isAnnotationPresent(Triggers.class)) {
			Trigger[] triggers = clazz.getAnnotation(Triggers.class).value();
			for (Trigger trigger : triggers) {
				triggerComponents.add(buildTriggerComponent(trigger));
			}
		}
	}
	
	private TriggerComponent buildTriggerComponent(Trigger trigger) {
		
		Class<?> event = trigger.event();
		checkEvent(event);
		
		TriggerComponent result = null;
		try {
			
			result = new TriggerComponent(event.newInstance(), trigger.moment(), trigger.operation());
			
		} catch (Exception e) {
			String errorMsg = String.format("Could not instantiate event %s", event.getName());
			Log.error(errorMsg, e);
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
			Log.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);			
		}
	}
}