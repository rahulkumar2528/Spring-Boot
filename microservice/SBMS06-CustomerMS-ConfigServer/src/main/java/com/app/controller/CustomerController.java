package com.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-api")
@RefreshScope
public class CustomerController {

	@Value("${dbuser}")
	private String uname;
	
	@Value("${dbpwd}")
	private String upwd;
	
	
	@GetMapping("/customer-data")
	public ResponseEntity<String> showCustomerData(){
		return new ResponseEntity<String>("Customer Data :: "+ uname + " | "+upwd, HttpStatus.OK);
	}
	
}
