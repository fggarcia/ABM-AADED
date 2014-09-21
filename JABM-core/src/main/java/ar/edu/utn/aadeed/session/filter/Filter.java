package ar.edu.utn.aadeed.session.filter;

public class Filter {

	private String fieldName;
	
	private Object value;
	
	public Filter(String fieldName, Object value) {
		this.fieldName = fieldName;
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
