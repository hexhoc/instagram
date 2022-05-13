package com.example.backend.validator;

import com.example.backend.annotation.PasswordMatches;
import com.example.backend.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean result = false;

        if (value instanceof SignupRequest signupRequest) {
            result = signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
        }
        return result;
    }
}
