package com.app.vo;

import java.util.Set;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
