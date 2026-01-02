package com.app.service;

public interface WalletService {

	public void transfer(Integer senderId, Integer receiverId, Integer amount);

}
