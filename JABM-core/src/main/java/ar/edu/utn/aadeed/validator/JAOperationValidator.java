package ar.edu.utn.aadeed.validator;

public interface JAOperationValidator<T> {

	public boolean validate(T value);

}