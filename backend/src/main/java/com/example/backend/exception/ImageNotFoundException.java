package com.example.backend.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String s) {
        super(s);
    }
}
