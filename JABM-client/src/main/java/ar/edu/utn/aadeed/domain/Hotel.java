package ar.edu.utn.aadeed.domain;

import ar.edu.utn.aadeed.annotation.Entity;
import ar.edu.utn.aadeed.annotation.Field;
import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.repository.impl.MemoryRepositoryFactory;

@Entity(repositoryFactory = MemoryRepositoryFactory.class)
public class Hotel {

	@View(order = 2, label = "Identificador")
	@Field(required = true, editable = false)
	private Long id;

	@View(order = 1)
	@Field
	private String name;

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
}
