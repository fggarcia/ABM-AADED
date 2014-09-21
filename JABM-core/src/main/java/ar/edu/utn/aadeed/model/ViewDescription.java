package ar.edu.utn.aadeed.model;

import ar.edu.utn.aadeed.view.ViewComponent;

public class ViewDescription {

	private ViewComponent component;
	
	private String label;

	private int order;
	
	private int size;
	
	public ViewDescription(ViewComponent component) {
		this.component = component;
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

	public ViewComponent getComponent() {
		return component;
	}
}
