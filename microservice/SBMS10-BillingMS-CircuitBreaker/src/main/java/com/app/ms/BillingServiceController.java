package com.app.ms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing-api")
public class BillingServiceController {

	@Value("${server.port}")
	private int port;
	
	@Value("${eureka.instance.instance-id}")
	private String instanceId;
	
	@GetMapping("/payment")
	public ResponseEntity<String> doPayment(){
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Payment can be done using cards, upi payment instance_id:: |"+instanceId+"| Port No:: |"+port+"|", HttpStatus.OK);	
	}
	
	@GetMapping("/payment/{cardNo}")
	public ResponseEntity<String> doPayment(@PathVariable("cardNo") long cardNo){
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Payment can be done using cards, upi payment card no: |"+cardNo +"| instance_id:: |"+instanceId+"| Port No:: |"+port+"|", HttpStatus.OK);	
	}
}
