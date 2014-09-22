package ar.edu.utn.aadeed.domain;

import static ar.edu.utn.aadeed.view.component.ViewComponent.TEXT_BOX;
import ar.edu.utn.aadeed.annotation.Descriptor;
import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.repository.memory.MemoryRepositoryFactory;

import com.google.common.base.Objects;

@Entity(repositoryFactory = MemoryRepositoryFactory.class)
public class Hotel {

	@View(order = 2, label = "Identificador", component = TEXT_BOX, size = 10)
	@Descriptor(required = true, editable = false, filter = true)
	private Long id;

	@View(order = 1, component = TEXT_BOX)
	@Descriptor
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