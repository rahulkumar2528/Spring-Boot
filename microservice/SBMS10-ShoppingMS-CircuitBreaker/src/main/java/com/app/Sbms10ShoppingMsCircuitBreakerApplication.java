package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Sbms10ShoppingMsCircuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sbms10ShoppingMsCircuitBreakerApplication.class, args);
	}

}
