package ar.edu.utn.aadeed.model;

public class JAFilter {

	private Object value;
	
	private JAFieldDescription field;
	
	public JAFilter(JAFieldDescription field, Object value) {
		this.field = field;
		this.value = value;
	}

	public JAFieldDescription getField() {
		return field;
	}
	
	public Object getValue() {
		return value;
	}
}