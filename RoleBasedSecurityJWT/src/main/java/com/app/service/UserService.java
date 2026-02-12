package com.app.service;

import java.util.List;

import com.app.entity.Users;
import com.app.vo.RegisterRequest;

public interface UserService {
	public Users registerUser(RegisterRequest request);
	
	public List<Users> getAllUses();
}
