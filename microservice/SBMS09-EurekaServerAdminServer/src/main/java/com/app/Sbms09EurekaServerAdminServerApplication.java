package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Sbms09EurekaServerAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sbms09EurekaServerAdminServerApplication.class, args);
	}

}
