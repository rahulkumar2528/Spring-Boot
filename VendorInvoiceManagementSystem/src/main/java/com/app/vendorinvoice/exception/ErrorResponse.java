package com.app.vendorinvoice.exception;

import java.time.LocalDateTime;


public class ErrorResponse {

	private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
