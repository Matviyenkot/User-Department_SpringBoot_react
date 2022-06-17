package com.example.ukeess_test_task_springboot.handlers;

public class NoSuchEntityException extends RuntimeException{
    public NoSuchEntityException(String message) {
        super(message);
    }
}
