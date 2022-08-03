package com.example.ukeess_test_task_springboot.handlers;

public class InvalidDataForRegistration extends RuntimeException{
    public InvalidDataForRegistration(String message) {
        super(message);
    }
}
