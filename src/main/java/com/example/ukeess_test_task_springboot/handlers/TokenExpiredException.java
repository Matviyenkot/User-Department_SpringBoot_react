package com.example.ukeess_test_task_springboot.handlers;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String message) {
        super(message);
    }
}
