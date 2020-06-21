package mil.dtic.ured.model;

import java.math.BigDecimal;

import mil.dtic.ured.util.ConstantUtil;
import mil.dtic.ured.validation.ValidDecimalNumber;
import mil.dtic.ured.validation.ValidEmail;


public class RequestInfo {

	@ValidEmail(min = 55, message = "Please enter a valid email.")
	private String email;

	@ValidDecimalNumber(min = ConstantUtil.DECIMALPOINT)
	private BigDecimal decimalNumber;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getDecimalNumber() {
		return decimalNumber;
	}

	public void setDecimalNumber(BigDecimal decimalNumber) {
		this.decimalNumber = decimalNumber;
	}
	
	

}
