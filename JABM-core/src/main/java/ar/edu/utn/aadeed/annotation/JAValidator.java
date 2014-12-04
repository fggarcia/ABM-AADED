package ar.edu.utn.aadeed.annotation;

import ar.edu.utn.aadeed.validator.JAOperation;
import ar.edu.utn.aadeed.validator.JAOperationValidator;

public @interface JAValidator {

	JAOperation operation();

	Class<? extends JAOperationValidator<?>> validator();

}