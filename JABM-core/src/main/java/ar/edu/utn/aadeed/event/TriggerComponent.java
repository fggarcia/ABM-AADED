package ar.edu.utn.aadeed.event;

public class TriggerComponent {

	private Object observer;

	private Moment moment;

	private Operation operation;

	public TriggerComponent(Object observer, Moment moment, Operation operation) {
		this.observer = observer;
		this.moment = moment;
		this.operation = operation;
	}

	public Object getObserver() {
		return observer;
	}

	public Moment getMoment() {
		return moment;
	}

	public Operation getOperation() {
		return operation;
	}
}
