package com.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		BCryptPasswordEncoder bed = new BCryptPasswordEncoder();
		String encode = bed.encode("Rahul");
		System.out.println(encode);
	}
}
