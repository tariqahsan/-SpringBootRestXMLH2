package mil.dtic.ured.model;

import java.math.BigDecimal;

import lombok.Data;
import mil.dtic.ured.util.ConstantUtil;
import mil.dtic.ured.validation.ValidDecimalNumber;
import mil.dtic.ured.validation.ValidEmail;

@Data
public class RequestInfo2 {

	@ValidEmail(min = 55, message = "Please enter a valid email.")
	private String email;

	@ValidDecimalNumber(min = ConstantUtil.DECIMALPOINT)
	private BigDecimal decimalNumber;

}
