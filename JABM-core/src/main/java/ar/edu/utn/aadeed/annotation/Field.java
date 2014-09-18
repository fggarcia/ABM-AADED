package ar.edu.utn.aadeed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD})
@Retention(RUNTIME)
public @interface Field {

    boolean required() default false;

    boolean editable() default true;
}
