package ar.edu.utn.aadeed.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ar.edu.utn.aadeed.event.Moment;
import ar.edu.utn.aadeed.event.Operation;

@Target({ TYPE })
@Retention(RUNTIME)
public @interface Triggers {

	public @interface Trigger {
		Operation operation();
		Moment moment();
		Class<?> action();
	}

	Trigger[] value();

}