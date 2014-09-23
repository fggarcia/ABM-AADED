package ar.edu.utn.aadeed.view.component;

import java.util.Date;

public enum JAViewType {

	CHECK_BOX(Boolean.class), TEXT_BOX(String.class, Number.class), 
	DATE_TIME_PICKER(String.class, Date.class), RADIO_BUTTON(Enum.class), 
	SELECT_ITEM(Enum.class), TEXT_AREA(String.class);

	private Class<?>[] javaTypes;

	JAViewType(Class<?>... javaTypes) {
		this.javaTypes = javaTypes;
	}

	public Class<?>[] getJavaTypes() {
		return javaTypes;
	}
}