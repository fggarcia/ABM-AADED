package ar.edu.utn.aadeed.domain;

import static ar.edu.utn.aadeed.view.component.JAViewType.TEXT_BOX;
import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.repository.memory.JAMemoryRepositoryFactory;

import com.google.common.base.MoreObjects;

@JAEntity(repositoryFactory = JAMemoryRepositoryFactory.class)
public class Room {

	@JAView(order = 2, label = "Identificador", type = TEXT_BOX)
	@JADescriptor(required = true, editable = false)
	private Long id;
	
	public Room(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).toString();
	}
}
