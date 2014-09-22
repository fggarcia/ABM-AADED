package ar.edu.utn.aadeed.model;

import ar.edu.utn.aadeed.view.component.JAViewType;

public class JAViewDescription {

	private JAViewType type;
	
	private String label;

	private int order;
	
	private int size;
	
	public JAViewDescription(JAViewType type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public JAViewType getType() {
		return type;
	}
}
