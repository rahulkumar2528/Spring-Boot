package com.app.service;

public interface UserService {

	void debit(Integer senderId, Integer amount);

	void credit(Integer receiverId, Integer amount);

}
