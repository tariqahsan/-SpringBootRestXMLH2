package mil.dtic.ured.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/*
 * JavaBean Validation - Class level constraints
 * https://www.logicbig.com/tutorials/java-ee-tutorial/bean-validation/class-level-constraints.html
 */

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PurchaseValidator.class)
@Documented
public @interface ValidPurchase {
    
	String message () default "total price must be 50 or greater for online order. " +
                        "Found: ${validatedValue.totalPrice}";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}





