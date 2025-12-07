package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/register")
	public String showReg() {
		return "UserRegister";
	}
	
	@PostMapping("/save")
	public String saveuser(@ModelAttribute User user, Model model) {
		Integer id = userService.saveUser(user);
		String message = "User '"+id+"' created!";
		model.addAttribute("message", message);
		return "UserRegister";
	}
	
}
