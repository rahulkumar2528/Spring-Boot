package com.app.vendorinvoice.config.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.vendorinvoice.entity.User;
import com.app.vendorinvoice.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;
	
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user
				.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).toList());
	}

}
