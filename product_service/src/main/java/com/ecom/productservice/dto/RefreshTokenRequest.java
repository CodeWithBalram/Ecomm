package com.ecom.productservice.dto;

public class RefreshTokenRequest {

    private String refreshToken;

    // Default Constructor
    public RefreshTokenRequest() {
    }

    // Parameterized Constructor
    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Getters and Setters (Yeh bohot zaroori hain Spring Boot ke liye)
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}