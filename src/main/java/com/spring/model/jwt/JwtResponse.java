package com.spring.model.jwt;

/**
 * Trả token cho người dùng account
 */
public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
