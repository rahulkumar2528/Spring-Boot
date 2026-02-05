package com.app.vendorinvoice.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7055826349436753667L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
