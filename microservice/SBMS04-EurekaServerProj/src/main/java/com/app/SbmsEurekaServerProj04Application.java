package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SbmsEurekaServerProj04Application {

	public static void main(String[] args) {
		SpringApplication.run(SbmsEurekaServerProj04Application.class, args);
	}

}
