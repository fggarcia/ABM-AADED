package ar.edu.utn.aadeed.domain;

import static ar.edu.utn.aadeed.view.component.JAViewType.TEXT_BOX;
import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.repository.memory.JAMemoryRepositoryFactory;

import com.google.common.base.Objects;

@JAEntity(repositoryFactory = JAMemoryRepositoryFactory.class)
public class Hotel {

	@JAView(order = 2, label = "Identificador", type = TEXT_BOX, size = 10)
	@JADescriptor(required = true, editable = false, filter = true)
	private Long id;

	@JAView(order = 1, type = TEXT_BOX)
	@JADescriptor(filter = true)
	private String name;

	@JAView(order = 4, type = TEXT_BOX)
	@JADescriptor(filter = true)
	private String address;
	
	@JAView(order = 3, type = TEXT_BOX)
	@JADescriptor(filter = true)
	private String hotelType;

	public Hotel(Long id, String name, String address, String hotelType) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.hotelType = hotelType;
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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("name", name).add("address", address).add("hotelType", hotelType).toString();
	}
}