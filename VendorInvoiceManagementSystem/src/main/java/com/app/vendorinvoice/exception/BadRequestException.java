package com.app.vendorinvoice.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 2885550838334507841L;

	 public BadRequestException(String message) {
	        super(message);
	    }
}
