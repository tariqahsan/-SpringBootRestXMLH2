package mil.dtic.ured.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mil.dtic.ured.model.Address;
import mil.dtic.ured.model.Country;

public class MultiCountryAddressValidator implements ConstraintValidator<ValidAddress, Address> {

	public void initialize(ValidAddress constraintAnnotation) {

	}

	@Override
	public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {

		Country country = address.getCountry();
		System.out.println("Country Code is --> " + country.getIso2());
		if (country == null || country.getIso2() == null || address.getZipCode() == null) {
			//return true;
			System.out.println("Returning FALSE!!!");
			return false;
		}

		switch (country.getIso2()) {
		case "FR":
			return true; // Check if address.getZipCode() is valid for France
		case "GR":
			return true; // Check if address.getZipCode() is valid for Greece
		default:
			return false;
		}
	}
	
	 /**
     * Validate zipcode and city depending on the country
     */
//    public boolean isValid(Address object, ConstraintValidatorContext context) {
//        if (!(object instanceof Address)) {
//            throw new IllegalArgumentException("@Address only applies to Address");
//        }
//        Address address = (Address) object;
//        Country country = address.getCountry();
//        if (country.getISO2() == "FR") {
//            // check address.getZipCode() structure for France (5 numbers)
//            // check zipcode and city correlation (calling an external service?)
//            return isValid;
//        } else if (country.getISO2() == "GR") {
//            // check address.getZipCode() structure for Greece
//            // no zipcode / city correlation available at the moment
//            return isValid;
//        }
//        // ...
//    }
}