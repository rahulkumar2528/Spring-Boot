package com.app.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Users;
import com.app.security.CustomUserDetailsService;
import com.app.security.JwtUtil;
import com.app.service.UserService;
import com.app.vo.AuthResponse;
import com.app.vo.LoginRequest;
import com.app.vo.RegisterRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

	public final AuthenticationManager authenticationManager;
	public final CustomUserDetailsService userDetailsService;
	public final JwtUtil jwtUtil;
	public final UserService userService;
	
	public AuthController(AuthenticationManager authenticationManager,
			CustomUserDetailsService userDetailsService,
			JwtUtil jwtUtil,
			UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}
	
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
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
	
	@GetMapping("/getAllUser")
    public ResponseEntity<List<Users>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUses());
    }
}
