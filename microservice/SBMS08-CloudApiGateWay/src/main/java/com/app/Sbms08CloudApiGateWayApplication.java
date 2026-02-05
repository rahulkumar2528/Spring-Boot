package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Sbms08CloudApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sbms08CloudApiGateWayApplication.class, args);
	}

}
