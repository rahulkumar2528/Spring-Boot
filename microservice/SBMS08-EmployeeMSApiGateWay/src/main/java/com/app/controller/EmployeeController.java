package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp-api")
public class EmployeeController {

	@GetMapping("/report")
	public ResponseEntity<String> showReport(){
		return new ResponseEntity<String>("Employee Report", HttpStatus.OK);
	}
}
