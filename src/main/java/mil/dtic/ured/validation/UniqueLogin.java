package mil.dtic.ured.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = mil.dtic.ured.validation.UniqueLoginValidator.class)
@Documented
public @interface UniqueLogin {
	
    //String message() default "{com.dolszewski.blog.UniqueLogin.message}";
    String message() default "The given login is already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
