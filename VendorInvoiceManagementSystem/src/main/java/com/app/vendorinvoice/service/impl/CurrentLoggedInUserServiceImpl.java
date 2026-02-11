package com.app.vendorinvoice.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.app.vendorinvoice.service.CurrentLoggedInUserService;

@Service
public class CurrentLoggedInUserServiceImpl implements CurrentLoggedInUserService {

	@Override
	public String getCurrentUserId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername().toLowerCase();
	}

}
