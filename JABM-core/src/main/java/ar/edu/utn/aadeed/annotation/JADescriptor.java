package ar.edu.utn.aadeed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD})
@Retention(RUNTIME)
public @interface JADescriptor {

    boolean required() default false;

    boolean editable() default true;
    
    boolean filter() default false;

    int maxLength() default -1;
    
    String regex() default "";
}