package com.app.clientcomp;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BillingServiceClient {
	
	@Autowired
	private LoadBalancerClient client;

	public String invokeDoPayment() {
		// get service instance obj representing provider ms from Eureka Server having less load factor
		ServiceInstance instance = client.choose("Billing-Service");
		
		// get details from service instance
		String url = instance.getUri() + "/billing-api/payment";
		
		//get RestTemplate class obj
		RestTemplate template = new RestTemplate();
		
		// invoke the service of the Provider Ms
		@Nullable
		String result = template.getForObject(url, String.class);
		
		return result;
	}
}
