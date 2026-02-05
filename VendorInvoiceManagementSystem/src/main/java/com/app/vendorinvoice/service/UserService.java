package com.app.vendorinvoice.service;

import com.app.vendorinvoice.dto.request.RegisterRequest;
import com.app.vendorinvoice.entity.User;

public interface UserService {

	public User registerUser(RegisterRequest request);
}
