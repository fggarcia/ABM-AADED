package ar.edu.utn.aadeed.session;

import java.util.List;
import java.util.Observable;

import ar.edu.utn.aadeed.event.Moment;
import ar.edu.utn.aadeed.event.Operation;
import ar.edu.utn.aadeed.event.TriggerComponent;
import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.eventbus.EventBus;

public class Session<T> extends Observable {

	private Repository<T> repository;

	private Table<Moment, Operation, EventBus> triggers = HashBasedTable.create();

	protected Session(Repository<T> repository, List<TriggerComponent> triggerComponents) {
		this.repository = repository;
		this.addTriggers(triggerComponents);
	}

	private void addTriggers(List<TriggerComponent> triggerComponents) {
		
		for (TriggerComponent triggerComponent : triggerComponents) {
			
			Moment moment = triggerComponent.getMoment();
			Operation operation = triggerComponent.getOperation();
			
			EventBus eventBus = triggers.get(moment, operation);
			if(eventBus == null) {
				eventBus = new EventBus("Pepe");
				triggers.put(moment, operation, eventBus);
			}
			
			eventBus.register(triggerComponent.getObserver());
		}
	}

	public boolean add(T object) {
		triggers.get(Moment.BEFORE, Operation.INSERT).post(object);
		boolean result = this.repository.add(object);
		return result;
	}

	public boolean remove(T object) {
		return this.repository.remove(object);
	}

	public void release() {
		this.repository.release();
	}
}