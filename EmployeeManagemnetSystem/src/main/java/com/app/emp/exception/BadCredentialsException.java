package com.app.emp.exception;

public class BadCredentialsException extends RuntimeException {

	private static final long serialVersionUID = -7883513765260637477L;
	
	public BadCredentialsException(String message) {
        super(message);
    }

}