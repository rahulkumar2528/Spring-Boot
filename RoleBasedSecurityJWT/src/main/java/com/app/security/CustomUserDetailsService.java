package com.app.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.entity.Users;
import com.app.repository.UsersRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final UsersRepository userRepo;
	
	public CustomUserDetailsService(UsersRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("User Not Found"));
//		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		return User.withUsername(user.getUserName()).password(user.getPassword()).authorities(Collections.emptyList())
				.build();
	}

}
