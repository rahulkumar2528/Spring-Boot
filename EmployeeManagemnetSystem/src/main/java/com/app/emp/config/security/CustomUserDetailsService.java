package com.app.emp.config.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.emp.entity.Employee;
import com.app.emp.repository.EmployeeRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final EmployeeRepository empRepo;
	
	public CustomUserDetailsService(EmployeeRepository empRepo) {
		this.empRepo=empRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = empRepo.findByEmpUserName(username).orElseThrow(() -> new RuntimeException("User Not Found"));
		return User.withUsername(emp.getEmpUserName()).password(emp.getEmpPassword()).authorities(Collections.emptyList())
				.build();
	}

}
