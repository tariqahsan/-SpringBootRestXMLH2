package mil.dtic.ured.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import mil.dtic.ured.model.Purchase;
import mil.dtic.ured.model.RequestInfo;
import mil.dtic.ured.repository.PurchaseRepository;
import mil.dtic.ured.util.ErrorMessage;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Validated
@RequestMapping("/api/rest")
public class PurchaseController {

	@Autowired
	PurchaseRepository repository;
	
	@Autowired
	MessageSource messageSource;

	@GetMapping("/purchases")
	public ResponseEntity<List<Purchase>> getAllPurchases() {
		List<Purchase> purchases = new ArrayList<>();
		try {
			repository.findAll().forEach(purchases::add);
			
			if (purchases.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(purchases, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/purchases/{id}")
	public ResponseEntity<Purchase> getPurchaseById(@PathVariable("id") long id) {
		Optional<Purchase> purchaseData = repository.findById(id);

		if (purchaseData.isPresent()) {
			return new ResponseEntity<>(purchaseData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/purchase")
	public ResponseEntity<Purchase> postPurchase(@RequestBody Purchase purchase) {
		System.out.println(purchase.getPrice() + " " +  purchase.getQuantity());
		try {
			Purchase purchase2 = repository.save(new Purchase(purchase.getPrice(), purchase.getQuantity()));
			System.out.println(purchase.getPrice() + " " +  purchase.getQuantity());
			return new ResponseEntity<>(purchase, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	

	//@PostMapping(value = "/purchases", consumes = {"application/xml", "application/json"})	
	//@Consumes({"application/xml", "application/json"})
	@PostMapping("/purchases")
	@ResponseBody
	public ResponseEntity<List<Purchase>> postPurchases(@RequestBody List<@Valid Purchase> purchases) {
		List<Purchase> purchases2 = new ArrayList<>();
		//try {
//			for (Purchase purchase : purchases) {
//				Purchase purchase2 = repository.save(new Purchase(purchase.getName(), purchase.getAddress(), purchase.getAge()));
//				System.out.println(purchase.getAddress());
//				purchases2.add(purchase2);
//			}
			purchases
            .stream()
            .forEach(purchase -> {            
				Purchase purchase2 = repository.save(new Purchase(purchase.getPrice(), purchase.getQuantity()));
				System.out.println(purchase.getPrice() + " " +  purchase.getQuantity());
				purchases2.add(purchase2);
            });
		//} catch (Exception e) {
			//return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		//}
		return new ResponseEntity<List<Purchase>>(purchases2, HttpStatus.CREATED);
		
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity create(@RequestBody @Valid final List<Purchase> purchases, BindingResult bindingResult) {
		List<String> errorList = new ArrayList<>();
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
		}

		//System.out.println(requestInfo.toString());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
//	@RequestMapping(value = "/purchases", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseBody
//	public ResponseEntity<List<Purchase>> postPurchases(@RequestBody List<Purchase> purchases) {
//		try {
//			for (Purchase purchase : purchases) {
//				repository.save(new Purchase(purchase.getName(), purchase.getAddress(), purchase.getAge()));
//				System.out.println(purchase.getAddress());
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//		}
//		return new ResponseEntity<List<Purchase>>(purchases, HttpStatus.CREATED);
//		
//	}

	@DeleteMapping("/purchases/{id}")
	public ResponseEntity<HttpStatus> deletePurchase(@PathVariable("id") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/purchases")
	public ResponseEntity<HttpStatus> deleteAllPurchases() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = "purchases/age/{age}")
	public ResponseEntity<List<Purchase>> findByAge(@PathVariable int age) {
		try {
			List<Purchase> purchases = (List<Purchase>) repository.findAll();

			if (purchases.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(purchases, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/purchases/{id}")
	public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") long id, @RequestBody Purchase purchase) {
		Optional<Purchase> purchaseData = repository.findById(id);

		if (purchaseData.isPresent()) {
			Purchase purchase1 = purchaseData.get();
			purchase1.setPrice(purchase1.getPrice());
			purchase1.setQuantity(purchase1.getQuantity());
			return new ResponseEntity<>(repository.save(purchase1), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
