package com.app.emp.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
        super(message);
    }
}
