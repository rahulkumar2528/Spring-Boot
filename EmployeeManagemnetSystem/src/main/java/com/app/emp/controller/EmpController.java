package com.app.emp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.config.security.CustomUserDetailsService;
import com.app.emp.config.security.JwtUtil;
import com.app.emp.exception.BadRequestException;
import com.app.emp.exception.ResourceNotFoundException;
import com.app.emp.request.EmpRequest;
import com.app.emp.request.LoginRequest;
import com.app.emp.response.AuthResponse;
import com.app.emp.response.EmpResponse;
import com.app.emp.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/emp")
@Slf4j
public class EmpController {

	public final AuthenticationManager authenticationManager;
	public final CustomUserDetailsService userDetailsService;
	public final JwtUtil jwtUtil;
	public final EmpService empService;
	
	public EmpController(AuthenticationManager authenticationManager,
			CustomUserDetailsService userDetailsService,
			JwtUtil jwtUtil, EmpService empService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.empService = empService;
	}
	
	@PostMapping("/createEmp")
    public ResponseEntity<EmpResponse> createEmployee(@RequestBody EmpRequest empRequest) throws BadRequestException, ResourceNotFoundException {
        EmpResponse emp = empService.createEmp(empRequest);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			log.info("Invalid Username or Password");
			throw new BadCredentialsException("Invalid Username or Password");
		}	
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String genrateToken = jwtUtil.genrateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(genrateToken, userDetails.getUsername()));
	}
	
	@GetMapping("/getEmp/{empId}")
	public ResponseEntity<EmpResponse> getEmployeeById(@PathVariable Integer empId) {
		EmpResponse emp = empService.getEmpByEmpId(empId);
		return ResponseEntity.ok(emp);
	}
	
	@GetMapping
	public ResponseEntity<List<EmpResponse>> getAllEmployees() {
		List<EmpResponse> employeePage = empService.getAllEmp();
		return ResponseEntity.ok(employeePage);
	}
	
	@PutMapping("/updateEmp/{empId}")
    public ResponseEntity<EmpResponse> updateEmployee(@PathVariable Integer empId, @RequestBody EmpRequest empRequest) {
        EmpResponse response = empService.updateEmp(empId, empRequest);
        return ResponseEntity.ok(response);
    }
	
	@DeleteMapping("/deleteEmp/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer empId) {
        return ResponseEntity.ok(empService.deleteEmp(empId));
    }
}
