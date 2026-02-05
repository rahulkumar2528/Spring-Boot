package com.app.vendorinvoice.exception;

public class UnauthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = -6023541660804551199L;

	public UnauthorizedException(String message) {
        super(message);
    }
}
