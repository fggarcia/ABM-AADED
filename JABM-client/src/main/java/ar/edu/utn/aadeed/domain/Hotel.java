package ar.edu.utn.aadeed.domain;

import ar.edu.utn.aadeed.action.PrintConsoleAction;
import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.annotation.Field;
import ar.edu.utn.aadeed.annotation.Trigger;
import ar.edu.utn.aadeed.annotation.Triggers;
import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.event.Moment;
import ar.edu.utn.aadeed.event.Operation;
import ar.edu.utn.aadeed.repository.impl.MemoryRepositoryFactory;

import com.google.common.base.Objects;

@Entity(repositoryFactory = MemoryRepositoryFactory.class)
@Triggers({ @Trigger(moment = Moment.BEFORE, operation = Operation.ADD, event = PrintConsoleAction.class) })
public class Hotel {

	@View(order = 2, label = "Identificador")
	@Field(required = true, editable = false)
	private Long id;

	@View(order = 1)
	@Field
	private String name;

	public Hotel(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("name", name).toString();
	}
}