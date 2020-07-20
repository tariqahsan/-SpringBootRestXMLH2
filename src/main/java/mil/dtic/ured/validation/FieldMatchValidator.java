package mil.dtic.ured.validation;

import javax.validation.ConstraintValidator;
import mil.dtic.ured.validation.FieldMatch;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
public final class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{
    private String firstFieldName;
    private String secondFieldName;

    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        System.out.println("firstFieldName = " + firstFieldName+"   secondFieldName = "+secondFieldName);
    }

    public boolean isValid(final Object value, final ConstraintValidatorContext cvc) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName );
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName );
            System.out.println("firstObj = " + firstObj + "   secondObj = " + secondObj);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception e) {
            System.out.println(e.toString());
        }
        return true;
    }
}
