package com.app.emp.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1421998366435331054L;

	public UnauthorizedException(String message) {
		super(message);
	}
}
