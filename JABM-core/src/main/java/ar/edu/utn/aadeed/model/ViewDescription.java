package ar.edu.utn.aadeed.model;

import ar.edu.utn.aadeed.view.ViewComponentOption;

public class ViewDescription {

	private FieldDescription field;

	private String label;

	private int order;
	
	private int size;
	
	private ViewComponentOption component;

	public ViewDescription(FieldDescription field) {
		this.field = field;
	}

	public FieldDescription getField() {
		return field;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ViewComponentOption getComponent() {
		return component;
	}

	public void setComponent(ViewComponentOption component) {
		this.component = component;
	}
}
