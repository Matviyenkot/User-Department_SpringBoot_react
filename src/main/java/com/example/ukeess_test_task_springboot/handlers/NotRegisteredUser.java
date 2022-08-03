package com.example.ukeess_test_task_springboot.handlers;

public class NotRegisteredUser extends RuntimeException{
    public NotRegisteredUser(String message) {
        super(message);
    }
}
