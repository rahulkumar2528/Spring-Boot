package com.app.exception;

public class NoSuchCustomerExistsException extends RuntimeException{

	private static final long serialVersionUID = 3823322378532932331L;
	
	private String message;
	
	public NoSuchCustomerExistsException() {
	}

	public NoSuchCustomerExistsException(String message) {
		super(message);
		this.message=message;
	}
	
	
}
