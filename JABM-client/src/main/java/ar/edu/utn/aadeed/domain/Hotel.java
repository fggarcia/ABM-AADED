package ar.edu.utn.aadeed.domain;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.repository.memory.JAMemoryRepositoryFactory;

import com.google.common.base.MoreObjects;

import java.util.Date;

import static ar.edu.utn.aadeed.view.component.JAViewType.*;

@JAEntity(repositoryFactory = JAMemoryRepositoryFactory.class)
public class Hotel {

	public enum Type {
		HOTEL, APPARTMENT;
	}

	@JAView(order = 2, label = "Identificador", type = TEXT_BOX, size = 10)
	@JADescriptor(required = true, editable = false, filter = true)
	private Long id;

	@JAView(order = 1, type = TEXT_BOX)
	@JADescriptor(filter = true, regex = "\\d+")
	private String name;

	@JAView(order = 4, type = TEXT_BOX)
	@JADescriptor(filter = false)
	private String address;
	
	@JAView(order = 3, type = SELECT_ITEM)
	@JADescriptor(filter = true)
	private Type hotelType;

	@JAView(order = 5, type = CHECK_BOX)
	@JADescriptor(filter = true)
	private Boolean preferred;

	@JAView(order = 6, type = DATE_TIME_PICKER)
	@JADescriptor(filter = true)
	private Date creation;
	
	public Hotel() { }

	public Hotel(Long id, String name, String address, Type hotelType, Boolean preferred,
				 Date creation) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.hotelType = hotelType;
		this.preferred = preferred;
		this.creation = creation;
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

	public Type getHotelType() {
		return hotelType;
	}

	public void setHotelType(Type hotelType) {
		this.hotelType = hotelType;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name).add("address", address)
				.add("hotelType", hotelType).add("preferred", preferred).add("creation", creation).toString();
	}
}