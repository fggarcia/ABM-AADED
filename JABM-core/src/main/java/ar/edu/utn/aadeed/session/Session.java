package ar.edu.utn.aadeed.session;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.event.Moment;
import ar.edu.utn.aadeed.event.Operation;
import ar.edu.utn.aadeed.event.TriggerComponent;
import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.eventbus.EventBus;

public class Session<T> {
	
	static final Logger Log = LoggerFactory.getLogger(Session.class);

	private Repository<T> repository;

	private Table<Moment, Operation, EventBus> triggers = HashBasedTable.create();

	protected Session(Repository<T> repository, List<TriggerComponent> triggerComponents) {
		this.repository = repository;
		this.addTriggers(triggerComponents);
	}

	public boolean add(T object) {
		boolean result = this.repository.add(object);
		return result;
	}

	public boolean remove(T object) {
		boolean result = this.repository.remove(object);
		return result;
	}
	
	public boolean update(T oldObject, T newObject) {
		boolean result = this.repository.update(oldObject, newObject);
		return result;
	}

	public int release() {
		int result = this.repository.release();
		return result;
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
		if(eventBus == null) {
			
			eventBus = new EventBus(triggerComponent.getName());
			triggers.put(triggerComponent.getMoment(), triggerComponent.getOperation(), eventBus);
		}
		
		return eventBus;
	}
	
	private void postEventChange(Moment moment, Operation operation, Object event) {
		EventBus eventBus = triggers.get(moment, operation);
		if(eventBus != null) {
			eventBus.post(event);
		}
	}
}