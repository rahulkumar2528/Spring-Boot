package com.app.service.impl;

import org.springframework.stereotype.Service;

import com.app.service.UserService;
import com.app.service.WalletService;

import jakarta.transaction.Transactional;

@Service
public class WalletServiceImpl implements WalletService {
	
	private UserService userService;
	
	public WalletServiceImpl(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	@Transactional
	public void transfer(Integer senderId, Integer receiverId, Integer amount) {
		userService.debit(senderId, amount);
		userService.credit(receiverId, amount);
	}

}
