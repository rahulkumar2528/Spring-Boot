package com.app.emp.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7883513765260637477L;
	
	public ResourceNotFoundException(String message) {
        super(message);
    }

}
