package ar.edu.utn.aadeed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ar.edu.utn.aadeed.view.component.JAViewType;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD})
@Retention(RUNTIME)
public @interface JAView {
	
    int order() default -1;

    String label() default "";
    
    JAViewType type();
    
    int size() default -1;

}
