package mil.dtic.ured.validation;

import javax.validation.Payload;

public @interface ValidateString {

    String[] acceptedValues();

    String message() default "Invalid string value";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { }; 
}
