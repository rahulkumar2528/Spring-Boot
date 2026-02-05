package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Sbms06CustomerMsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sbms06CustomerMsConfigServerApplication.class, args);
	}

}
