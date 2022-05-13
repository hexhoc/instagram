package com.example.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @Email(message = "Email field should have email format")
    @NotBlank(message = "User email is required")
//    @ValidEmail
    private String email;
    @NotEmpty(message = "First name is required")
    private String firstname;
    @NotEmpty(message = "Last name is required")
    private String lastname;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password min length = 6")
    private String password;
    private String confirmPassword;

}
