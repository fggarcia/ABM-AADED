package ar.edu.utn.aadeed.event;

import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.eventbus.EventBus;

public class EventBusManager {

	private Table<Moment, Operation, EventBus> triggers = HashBasedTable.create();
	
	public EventBusManager(List<TriggerComponent> triggerComponents) {
		addTriggers(triggerComponents);
	}
	
	public void postEventChange(Moment moment, Operation operation, Object event) {
		EventBus eventBus = triggers.get(moment, operation);
		if(eventBus != null) {
			eventBus.post(event);
		}
	}
	
	private void addTriggers(List<TriggerComponent> triggerComponents) {
		for (TriggerComponent triggerComponent : triggerComponents) {
			this.addTrigger(triggerComponent);
		}
	}
	
	private void addTrigger(TriggerComponent triggerComponent) {
		findOrCreateEventBus(triggerComponent).register(triggerComponent.getEvent());
	}
	
	private EventBus findOrCreateEventBus(TriggerComponent triggerComponent) {
		EventBus eventBus = triggers.get(triggerComponent.getMoment(), triggerComponent.getOperation());
		return (eventBus != null) ? eventBus : createEventBus(triggerComponent);
	}
	
	private EventBus createEventBus(TriggerComponent triggerComponent) {
		EventBus eventBus = new EventBus(triggerComponent.getName());
		triggers.put(triggerComponent.getMoment(), triggerComponent.getOperation(), eventBus);
		return eventBus;
	}
}