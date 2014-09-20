package ar.edu.utn.aadeed.session;

import java.util.List;

import ar.edu.utn.aadeed.event.EventBusManager;
import ar.edu.utn.aadeed.event.TriggerComponent;
import ar.edu.utn.aadeed.repository.Repository;

public class Session<T> {
	
	private Repository<T> repository;
	
	private EventBusManager busManager;

	protected Session(Repository<T> repository, List<TriggerComponent> triggerComponents) {
		this.repository = repository;
		this.busManager = new EventBusManager(triggerComponents);
	}

	public boolean add(T newObject) {
		boolean result = this.repository.add(newObject);
		return result;
	}

	public boolean remove(T oldObject) {
		boolean result = this.repository.remove(oldObject);
		return result;
	}
	
	public boolean update(T oldObject, T newObject) {
		boolean result = this.repository.update(oldObject, newObject);
		return result;
	}

	public int release() {
		return this.repository.release();
	}
}