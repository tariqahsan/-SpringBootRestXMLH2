package mil.dtic.ured.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mil.dtic.ured.model.Movie;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<Movie>> {

    @Override
    public boolean isValid(List<Movie> values, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (values.size() > 4) {
            isValid = false;
        }
        return isValid;
    }

}
