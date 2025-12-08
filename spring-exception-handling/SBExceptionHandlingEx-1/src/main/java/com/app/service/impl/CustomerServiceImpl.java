package com.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Customer;
import com.app.exception.CustomerAlreadyExistsException;
import com.app.exception.NoSuchCustomerExistsException;
import com.app.repository.CustomerRepository;
import com.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	 @Autowired
	 private CustomerRepository customerRespository;
	
	@Override
	public Customer getCustomer(Long id) {
		return customerRespository.findById(id)
				.orElseThrow(() -> new NoSuchCustomerExistsException("NO CUSTOMER PRESENT WITH ID: " + id));
	}

	@Override
	public String addCustomer(Customer customer) {
		Optional<Customer> existCustomer = customerRespository.findByName(customer.getName());
		if(existCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already exists!!");
		}
		customerRespository.save(customer);
		return "Customer added successfully";
	}

	@Override
	public String updateCustomer(Customer customer) {
		Optional<Customer> existCustomer = customerRespository.findById(customer.getId());
		if(!existCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("No Such Customer exists!!");
		}
		existCustomer.get().setName(customer.getName());
		existCustomer.get().setAddress(customer.getAddress());
        customerRespository.save(existCustomer.get());
		return "Record updated Successfully";
	}

}
