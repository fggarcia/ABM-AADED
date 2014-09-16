package ar.edu.utn.aadeed.event;

import com.google.common.base.Objects;

public class TriggerComponent {

	private Object event;

	private Moment moment;

	private Operation operation;

	public TriggerComponent(Object event, Moment moment, Operation operation) {
		this.event = event;
		this.moment = moment;
		this.operation = operation;
	}
	
	public String getName() {
		return this.toString();
	}
	
	public Object getEvent() {
		return event;
	}

	public Moment getMoment() {
		return moment;
	}

	public Operation getOperation() {
		return operation;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("moment", moment).add("operation", operation).toString();
	}
}
