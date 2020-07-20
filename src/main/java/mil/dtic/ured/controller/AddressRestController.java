package mil.dtic.ured.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import mil.dtic.ured.util.ErrorMessage;
import mil.dtic.ured.util.Messages;
import mil.dtic.ured.model.Address;
import mil.dtic.ured.model.RequestInfo;

@RestController
@RequestMapping("/rest/address")
public class AddressRestController {
	
//	@Autowired
//	Messages messages;
//	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String requestInfo(RequestInfo requestInfo) {
		return "request";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String address(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "request";
		}

		System.out.println(address.toString());

		return "successful";
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity create(@RequestBody @Valid final Address address, BindingResult bindingResult) {
		List<String> errorList = new ArrayList<>();
		List<ErrorMessage> errorMessages = new ArrayList<>();
		System.out.println(errorList.size());
		System.out.println(errorMessages.size());
		if (bindingResult.hasErrors()) {
			 bindingResult.getFieldErrors().forEach(fieldError ->
	            errorList.add(fieldError.getField() + ": " + messageSource.getMessage(fieldError, Locale.US))
	        );
			 bindingResult
	            .getFieldErrors()
	            .stream()
	            .forEach(fieldError -> {            
	            	ErrorMessage errorMessage = new ErrorMessage(messageSource.getMessage(fieldError, Locale.US), fieldError.getField());           	
	            	System.out.println(errorMessage.getMessage());
	            	System.out.println(errorMessage.getFieldName());
	            	errorMessages.add(errorMessage);
	            });
			 return new ResponseEntity<>(errorMessages, HttpStatus.NOT_ACCEPTABLE);
		}

		System.out.println(address.toString());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
