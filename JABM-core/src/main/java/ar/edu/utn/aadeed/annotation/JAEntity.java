package ar.edu.utn.aadeed.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ar.edu.utn.aadeed.repository.JARepositoryFactory;

@Target({ TYPE })
@Retention(RUNTIME)
public @interface JAEntity {

	Class<? extends JARepositoryFactory> repositoryFactory();
	
}