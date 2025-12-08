package com.app.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = -875407992280522064L;
	
	private String message;

	public CustomerAlreadyExistsException() {
	}

	public CustomerAlreadyExistsException(String msg) {
		super(msg);
		this.message = msg;
	}
}
