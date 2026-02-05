package com.app.vendorinvoice.controller;



import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.vendorinvoice.config.security.CustomUserDetailsService;
import com.app.vendorinvoice.config.security.JwtUtil;
import com.app.vendorinvoice.constants.Constants;
import com.app.vendorinvoice.dto.request.LoginRequest;
import com.app.vendorinvoice.dto.request.RegisterRequest;
import com.app.vendorinvoice.dto.response.AuthResponse;
import com.app.vendorinvoice.exception.UnauthorizedException;
import com.app.vendorinvoice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.CONT_AUTH)
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
			throw new UnauthorizedException("Invalid Username or Password");
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String genrateToken = jwtUtil.genrateToken(userDetails);
		Set<String> roles = userDetails.getAuthorities().stream().map(auth -> auth.getAuthority())
				.collect(Collectors.toSet());
		return ResponseEntity.ok(new AuthResponse(genrateToken, userDetails.getUsername(), roles));
	}
}
