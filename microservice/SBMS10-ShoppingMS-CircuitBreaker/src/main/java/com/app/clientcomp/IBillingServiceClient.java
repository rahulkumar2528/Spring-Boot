package com.app.clientcomp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BillingMS-CircuitBreaker")
public interface IBillingServiceClient {
	
	@GetMapping("/billing-api/payment")
	public String invokeDoPayment();
	
	@GetMapping("/billing-api/payment/{cardNo}")
	public String invokeDoPaymentWithCard(@PathVariable("cardNo") long cardNo);
	
}
