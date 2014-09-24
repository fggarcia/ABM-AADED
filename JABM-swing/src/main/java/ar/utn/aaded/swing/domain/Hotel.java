package ar.utn.aaded.swing.domain;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.repository.memory.JAMemoryRepositoryFactory;
import com.google.common.base.Objects;

import static ar.edu.utn.aadeed.view.component.JAViewType.TEXT_BOX;

@JAEntity(repositoryFactory = JAMemoryRepositoryFactory.class)
public class Hotel {

	@JAView(order = 2, label = "Identificador", type = TEXT_BOX, size = 10)
	@JADescriptor(required = true, editable = false, filter = true)
	private Long id;

	@JAView(order = 1, type = TEXT_BOX)
	@JADescriptor
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