package ar.edu.utn.aadeed.domain;

import static ar.edu.utn.aadeed.view.component.JAViewType.CHECK_BOX;
import static ar.edu.utn.aadeed.view.component.JAViewType.DATE_TIME_PICKER;
import static ar.edu.utn.aadeed.view.component.JAViewType.SELECT_ITEM;
import static ar.edu.utn.aadeed.view.component.JAViewType.TEXT_BOX;

import java.math.BigDecimal;
import java.util.Date;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.annotation.JAValidator;
import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.repository.memory.JAMemoryRepositoryFactory;
import ar.edu.utn.aadeed.validator.JACityValidator;
import ar.edu.utn.aadeed.view.component.JAViewType;

import com.google.common.base.MoreObjects;

@JAEntity(repositoryFactory = JAMemoryRepositoryFactory.class)
public class Hotel {

	public enum Type {
		HOTEL, APPARTMENT;
	}

	@JAView(order = 2, label = "Identificador", type = TEXT_BOX)
	@JADescriptor(required = true, editable = false, filter = true)
	private Long id;
	
	@JAView(order = 1, type = TEXT_BOX)
	@JADescriptor(filter = true, required = true, editable = true, regex = ".{2,18}")
	private String name;

	@JAView(order = 4, type = TEXT_BOX)
	@JADescriptor(filter = false, editable = true, maxLength = 22)
	private String address;
	
	@JAView(order = 3, type = SELECT_ITEM)
	@JADescriptor(filter = true, editable = true, required = true)
	private Type hotelType;

	@JAView(order = 5, type = CHECK_BOX)
	@JADescriptor(filter = true, editable = false, required = true)
	private Boolean preferred;
	
	@JAView(order = 6, type = DATE_TIME_PICKER)
	@JADescriptor(filter = true, editable = true, required = true)
	private Date creation;
	
	@JAView(order = 7, label = "Rating", type = TEXT_BOX)
	@JADescriptor(required = false, editable = false, filter = true)
	private BigDecimal rate;
	
	@JAView(order = 9, type = JAViewType.TEXT_BOX)
	@JADescriptor(required = true, editable = true, filter = true,
		validators = {
				@JAValidator(validator = JACityValidator.class)
		})
	private Long cityId;
	
	public Hotel() { }

	public Hotel(Long id, String name, String address, Type hotelType, Boolean preferred, Date creation, Long cityId) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.hotelType = hotelType;
		this.preferred = preferred;
		this.creation = creation;
		this.cityId = cityId;
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
	
	public BigDecimal getRate() {
		return rate;
	}
	
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name).add("address", address)
				.add("hotelType", hotelType).add("preferred", preferred).add("creation", creation).add("rate", rate)
				.add("cityId", cityId).toString();
	}
}