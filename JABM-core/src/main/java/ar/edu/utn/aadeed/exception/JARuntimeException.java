package ar.edu.utn.aadeed.exception;

public class JARuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JARuntimeException() {
	}
	
	public JARuntimeException(String message) {
		super(message);
	}
	
	public JARuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
