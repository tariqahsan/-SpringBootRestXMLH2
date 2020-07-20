package mil.dtic.ured.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mil.dtic.ured.model.Purchase;

/*
 * JavaBean Validation - Class level constraints
 * https://www.logicbig.com/tutorials/java-ee-tutorial/bean-validation/class-level-constraints.html
 */

public class PurchaseValidator implements ConstraintValidator<ValidPurchase, Purchase> {

	@Override
	public void initialize (ValidPurchase constraintAnnotation) {

	}

	@Override
	public boolean isValid (Purchase purchase,
			ConstraintValidatorContext context) {
		if (purchase.getPrice() == null || purchase.getQuantity() == null) {
			return false;
		}
		return purchase.getTotalPrice()
				.compareTo(new BigDecimal(50)) >= 0;

	}
}

