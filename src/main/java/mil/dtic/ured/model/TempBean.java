package mil.dtic.ured.model;

import org.hibernate.validator.constraints.NotEmpty;

import mil.dtic.ured.validation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match", groups={TempBean.ValidationGroup.class})
})
public final class TempBean
{        
    @NotEmpty(groups={ValidationGroup.class}, message="Might not be left blank.")
    private String password;
    
    @NotEmpty(groups={ValidationGroup.class}, message="Might not be left blank.")
    private String confirmPassword;

    public interface ValidationGroup {}

    //Getters and setters
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	};
    
}
