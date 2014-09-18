package ar.edu.utn.aadeed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD})
@Retention(RUNTIME)
public @interface View {

    int order() default -1;

    String label() default "";

    boolean filter() default false;
}
