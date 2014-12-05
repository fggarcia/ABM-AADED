package ar.edu.utn.aadeed.annotation;

import ar.edu.utn.aadeed.validator.JAOperationValidator;

public @interface JAValidator {

	Class<? extends JAOperationValidator> validator();

}