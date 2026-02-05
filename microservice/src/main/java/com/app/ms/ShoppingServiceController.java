package com.app.ms;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.clientcomp.IBillingServiceClient;

@RestController
@RequestMapping("/shopping-api")
public class ShoppingServiceController {

	private IBillingServiceClient client;
	
	public ShoppingServiceController(IBillingServiceClient client) {
		this.client=client;
	}
	
	@GetMapping("/purchase")
	public ResponseEntity<String> shopping(){
		System.out.println("client comp object class name :: "+client.getClass());
		//generate bill amount 
		double amt = new Random().nextInt(400000);
		// invoke provider MS
		String resultMsg = client.invokeDoPayment();
		//return final output
		String finalMsg = "Shopping is done amount:: "+amt+"...."+resultMsg;
		return new ResponseEntity<>(finalMsg, HttpStatus.OK);
	}
	
	@GetMapping("/purchase/{cardNo}")
	public ResponseEntity<String> shopping(@PathVariable("cardNo") long cardNo){
		//generate bill amount 
		double amt = new Random().nextInt(400000); 
		// invoke provider MS
		String resultMsg = client.invokeDoPaymentWithCard(cardNo);
		//return final output
		String finalMsg = "Shopping is done amount:: "+amt+"...."+resultMsg;
		return new ResponseEntity<>(finalMsg, HttpStatus.OK);
	}
}
