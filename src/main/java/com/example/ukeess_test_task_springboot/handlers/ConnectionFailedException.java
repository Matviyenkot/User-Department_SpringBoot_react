package com.example.ukeess_test_task_springboot.handlers;

public class ConnectionFailedException extends RuntimeException{
    public ConnectionFailedException(String message) {
        super(message);
    }
}
