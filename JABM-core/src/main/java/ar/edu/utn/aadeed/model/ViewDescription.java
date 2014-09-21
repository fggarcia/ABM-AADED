package ar.edu.utn.aadeed.model;

public class ViewDescription {

	private FieldDescription field;

	private String label;

	private int order;

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
}
