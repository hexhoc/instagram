package com.example.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "Username should be empty")
    private String username;
    @NotEmpty(message = "Password should be empty")
    private String password;
}
