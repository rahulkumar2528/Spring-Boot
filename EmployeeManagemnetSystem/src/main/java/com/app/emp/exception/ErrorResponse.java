package com.app.emp.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private LocalDateTime time;
    
	public ErrorResponse(int status, String message, String path, LocalDateTime time) {
		super();
		this.status = status;
		this.message = message;
		this.path = path;
		this.time = time;
	} 
}
