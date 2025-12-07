package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.requestMatchers("/home", "/", "/user/**").permitAll()
				.requestMatchers("/admin").hasAuthority("ADMIN")
				// .antMatchers("/**").hasAuthority("ADMIN") //ADMIN CAN ACCESS EVERY URL
				.requestMatchers("/customer").hasAuthority("CUSTOMER").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/hello", true))
				.logout(logout -> logout.permitAll());

		return http.build();
	}

	// ✅ 1️⃣ AuthenticationManager bean
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	// ✅ 2️⃣ PasswordEncoder bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ✅ 3️⃣ UserDetailsService bean
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}

	// ✅ 4️⃣ AuthenticationProvider bean
	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
}
