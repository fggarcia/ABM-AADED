package ar.edu.utn.aadeed.domain;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.annotation.Descriptor;
import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.repository.impl.MemoryRepositoryFactory;

import com.google.common.base.Objects;

@Entity(repositoryFactory = MemoryRepositoryFactory.class)
public class Room {

	@View(order = 2, label = "Identificador")
	@Descriptor(required = true, editable = false)
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
		return Objects.toStringHelper(this).add("id", id).toString();
	}
}
