package com.app.ms;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.clientcomp.BillingServiceClient;

@RestController
@RequestMapping("/shopping-api")
public class ShoppingServiceController {

	@Autowired
	private BillingServiceClient client;
	
	@GetMapping("/purchase")
	public ResponseEntity<String> shopping(){
		//generate bill amount 
		double amt = new Random().nextInt(400000);
		// invoke provider MS
		String resultMsg = client.invokeDoPayment();
		//return final output
		String finalMsg = "Shopping is done amount:: "+amt+"...."+resultMsg;
		return new ResponseEntity<>(finalMsg, HttpStatus.OK);
	}
}
