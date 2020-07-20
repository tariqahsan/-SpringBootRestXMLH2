package mil.dtic.ured.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = { MultiCountryAddressValidator.class })
public @interface ValidAddress {

//    String message() default "{com.example.validation.ValidAddress.message}";
	String message() default "Invalid country";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
