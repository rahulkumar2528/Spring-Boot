package com.app.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.Users;
import com.app.repository.UsersRepository;
import com.app.service.UserService;
import com.app.vo.RegisterRequest;

@Service
public class UserServiceImpl implements UserService {

	private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Users registerUser(RegisterRequest request) {
        if(userRepository.findByUserName(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        Users user = new Users();
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(1);
        return userRepository.save(user);
    }

	@Override
	public List<Users> getAllUses() {
		return userRepository.findAll();
	}
}
