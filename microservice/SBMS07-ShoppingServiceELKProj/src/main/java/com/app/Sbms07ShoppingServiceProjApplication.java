package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class Sbms07ShoppingServiceProjApplication {
	public static void main(String[] args) {
		log.debug("------Start Eureka Sever ELK main () method ------");
		SpringApplication.run(Sbms07ShoppingServiceProjApplication.class, args);
		log.debug("------End Eureka Sever ELK main () method ------");
	}

}
