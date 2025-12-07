package com.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired(required = true)
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Integer saveUser(User user) {

		String encPwd = passwordEncoder.encode(user.getUserPwd());
		user.setUserPwd(encPwd);
		return userRepo.save(user).getUserId();
	}

	@Override
	public Optional<User> getOneUser(Integer id) {

		return userRepo.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepo.findByUserEmail(username);
		if(optUser.isEmpty()) {
			throw new UsernameNotFoundException(username + " not exist"); 
		}
		User user = optUser.get();
		List<SimpleGrantedAuthority> authorities = user.getUserRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(username, user.getUserPwd(), authorities);
	}

}
