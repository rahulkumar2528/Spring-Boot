package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class Sbms09AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sbms09AdminServerApplication.class, args);
	}

}
