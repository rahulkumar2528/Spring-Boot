package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Customer;
import com.app.exception.CustomerAlreadyExistsException;
import com.app.exception.ErrorResponse;
import com.app.exception.NoSuchCustomerExistsException;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody Customer customer) {	
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) {
		return customerService.getCustomer(id);
	}
	
	@PutMapping("/updateCustomer")
	public String updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}
	
//	@ExceptionHandler(CustomerAlreadyExistsException.class)
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public ErrorResponse handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
//		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
//	}
}
