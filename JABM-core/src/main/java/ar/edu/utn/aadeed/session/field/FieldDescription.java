package ar.edu.utn.aadeed.session.field;

public class FieldDescription {

	private String name;

	private boolean required;;

	private boolean editable;

	private boolean filter;

	public FieldDescription(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}
}
