package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class Sbms07BillingServiceProjApplication {
	public static void main(String[] args) {
		log.debug("------Start Eureka Sever ELK main () method ------");
		SpringApplication.run(Sbms07BillingServiceProjApplication.class, args);
		log.debug("------End of Eureka Sever ELK main () method ------");
	}

}
