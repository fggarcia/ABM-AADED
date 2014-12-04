package ar.edu.utn.aadeed.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface JADescriptor {

	boolean required() default false;

	boolean editable() default true;

	boolean filter() default false;

	int maxLength() default -1;

	String regex() default "";

	public JAValidator[] validators() default {};
}