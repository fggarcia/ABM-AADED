package ar.edu.utn.aadeed.validator;

import ar.edu.utn.aadeed.exception.JAException;

public class JATestValidator extends JAOperationValidator {

	@Override
	public void validateOnDelete(final Object value) throws JAException {
		throw new JAException("Test");
	}
}