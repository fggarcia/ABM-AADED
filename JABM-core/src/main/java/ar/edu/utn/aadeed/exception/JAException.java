package ar.edu.utn.aadeed.exception;

public class JAException extends Exception {

	private static final long serialVersionUID = 1L;

	public JAException(String message) {
		super(message);
	}
	
	public JAException(String message, Throwable cause) {
		super(message, cause);
	}
}
