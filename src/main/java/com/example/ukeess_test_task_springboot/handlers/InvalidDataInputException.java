package com.example.ukeess_test_task_springboot.handlers;

public class InvalidDataInputException extends RuntimeException{
    public InvalidDataInputException(String message) {
        super(message);
    }
}
