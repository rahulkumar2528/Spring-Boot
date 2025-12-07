package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 2. Authorization
	@Bean
	SecurityFilterChain configurePath(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> 
		                           request.requestMatchers("/",  "/home").permitAll()
		                           .anyRequest().authenticated())
		                           .formLogin(form -> 
		                           form.loginPage("/login")
		                           .permitAll())
		                           .logout(logout -> logout.permitAll());
		return http.build();
	}
	
	// 1. Authentication (InMemory)
	@SuppressWarnings("deprecation")
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder().username("rahul").password("rahul").roles("ADMIN").build();
		UserDetails userDetails2 = User.withDefaultPasswordEncoder().username("ram").password("ram").roles("CUSTOMER").build();
		return new InMemoryUserDetailsManager(userDetails, userDetails2);
	}
	
}
