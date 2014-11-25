package ar.edu.utn.aadeed.model;

import ar.edu.utn.aadeed.view.component.JAViewType;

public class JAViewDescription {

	private JAViewType type;
	
	private String label;

	private int order;
	
	private String regularExpression;
	
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
	
	public String getRegularExpression() {
		return regularExpression;
	}
	
	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public JAViewType getType() {
		return type;
	}
}
