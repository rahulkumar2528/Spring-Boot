package com.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	private WalletService walletService;
	
	public WalletController(WalletService walletService) {
		this.walletService =  walletService;
	}
	
	@PostMapping("/transfer")
	public String transfer(@RequestParam Integer senderId,
			               @RequestParam Integer receiverId,
			               @RequestParam Integer amount) {
		
		try {
			walletService.transfer(senderId, receiverId, amount);
		} catch (Exception e) {
			return "Transfer failed: " + e.getMessage();
		}
		
		return "Transfer Completed";
	}
	
}
