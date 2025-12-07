package com.app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//2. Authorization
	@Bean
	SecurityFilterChain configurPath(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.requestMatchers("/home","/").permitAll()
				.requestMatchers("/admin").hasAuthority("ADMIN")
				.requestMatchers("/customer").hasAuthority("CUSTOMER")
				.anyRequest().authenticated()
				)
		   .formLogin(form -> form.loginPage("/login").permitAll()
				   .defaultSuccessUrl("/hello", true)
				   )
		   .logout(logout -> logout.permitAll());	
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		UserDetails user1 = User.withUsername("sam").password("$2a$10$TD7ldmKUQw3EHFxVivyA8OUrzy7butY9QDRnltnBS/b9aI0j6reYq")
		.authorities("ADMIN").build();
		
		UserDetails user2 = User.withUsername("ram").password("$2a$10$dEm8gdOC0R2S7IgXSnBKFOSeeKbCNuVeMC/hP24eY7zdADlUif4n.")
		.authorities("CUSTOMER").build();
		
		JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource);
		userManager.createUser(user1);
		userManager.createUser(user2);
		
		return userManager;
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
