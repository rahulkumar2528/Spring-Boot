package com.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp-api")
@RefreshScope
public class EmpController {

	@Value("${dbuser}")
	private String uname;
	
	@Value("${dbpwd}")
	private String upwd;
	
	@GetMapping("/emp-data")
	public ResponseEntity<String> showData(){
		return new ResponseEntity<String>("Data is :: " +uname + " | "+ upwd, HttpStatus.OK);
	}
}
