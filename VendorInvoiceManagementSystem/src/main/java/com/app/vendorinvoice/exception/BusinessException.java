package com.app.vendorinvoice.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -4653690435014441L;

	public BusinessException(String message) {
        super(message);
    }
}
