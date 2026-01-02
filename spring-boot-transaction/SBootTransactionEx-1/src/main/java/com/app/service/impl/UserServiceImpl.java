package com.app.service.impl;

import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public void debit(Integer senderId, Integer amount) {	
		User user = userRepo.findById(senderId).orElseThrow();
		user.setAmount(user.getAmount()-amount);
		userRepo.save(user);
	}

	@Override
	public void credit(Integer receiverId, Integer amount) {
		User user = userRepo.findById(receiverId).orElseThrow();
		user.setAmount(user.getAmount()+amount);
		userRepo.save(user);
	
	}

}
