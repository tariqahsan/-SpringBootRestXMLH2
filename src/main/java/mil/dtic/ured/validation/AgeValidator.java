package mil.dtic.ured.validation;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;

import java.time.Instant;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

import java.util.Date;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    protected long minAge;

    @Override

    public void initialize(Age ageValue) {

        this.minAge = ageValue.value();

    }

    @Override

    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {

        // null values are valid

        if ( date == null ) {

            return true;

        }

        LocalDate today = LocalDate.now();

        return ChronoUnit.YEARS.between(date, today)>=minAge;

    }

}