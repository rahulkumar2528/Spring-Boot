package com.app.emp.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7883513765260637477L;
	
	public BadRequestException(String message) {
        super(message);
    }

}
