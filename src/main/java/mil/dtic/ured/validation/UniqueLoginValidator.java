package mil.dtic.ured.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mil.dtic.ured.repository.LoginRepository;

class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
	 
    private LoginRepository loginRepository;
 
    public UniqueLoginValidator(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
 
    public void initialize(UniqueLogin constraint) {
    }
 
    public boolean isValid(String login, ConstraintValidatorContext context) {
//        return login != null && !loginRepository.findByLogin(login).isPresent();
    	
    	return login != null && !loginRepository.existsById(login);

    }
 
}