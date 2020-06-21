package mil.dtic.ured.exception;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * https://github.com/eugenp/tutorials/blob/master/spring-mvc-basics-3/src/main/java/com/baeldung/validation/listvalidation/controller/MovieController.java
 */

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mil.dtic.ured.util.ErrorMessage;

@RestControllerAdvice
public class ConstraintViolationExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);

	//    @ExceptionHandler(ConstraintViolationException.class)
	//    public ResponseEntity<String> handle(ConstraintViolationException constraintViolationException) {
	//        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
	//        String errorMessage = "";
	//        if (!violations.isEmpty()) {
	//            StringBuilder builder = new StringBuilder();
	//            violations.forEach(violation -> builder.append("\n" + violation.getMessage() + " " + violation.getPropertyPath()));
	//            //violations.forEach(violation -> builder.append("\n" + violation.toString()));
	//            errorMessage = builder.toString();
	//            System.out.println("errorMessage -> " + errorMessage);
	//            System.out.println("errorMessage -> end ...");
	//            
	//        } else {
	//            errorMessage = "ConstraintViolationException occured.";
	//        }
	//
	//        logger.error(errorMessage);
	//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	//    }

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Set<String>> handleConstraintViolation(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

		Set<String> messages = new HashSet<>(constraintViolations.size());
		messages.addAll(constraintViolations.stream()
				.map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
						constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
				.collect(Collectors.toList()));

		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);

	}

	//    @ExceptionHandler(MethodArgumentNotValidException.class)
	//    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
	//
	//        //List<ErrorDetails> errors = new ArrayList<>();
	//    	List<ErrorMessage> errors = new ArrayList<>();
	//System.out.println(errors.size());
	//        if (exception.getBindingResult().hasErrors()) {
	//            exception.getBindingResult().getFieldErrors().forEach(error -> {
	////                errors.add(new ErrorDetails(error.getField(), error.getDefaultMessage()));
	//            	errors.add(new ErrorMessage(error.getField(), error.getDefaultMessage()));
	//            	System.out.println(error.getField() + " " + error.getDefaultMessage());
	//            });
	//        }
	//
	//        return ResponseEntity.unprocessableEntity().body(errors);
	//    }

	/*
	 * List<String> errorList = new ArrayList<>();
		List<ErrorMessage> errorMessages = new ArrayList<>();
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
	 */

}