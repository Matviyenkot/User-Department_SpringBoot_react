package com.example.ukeess_test_task_springboot.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(ConnectionFailedException connectionFailedException){

        IncorrectData data = new IncorrectData();
        data.setInfo(connectionFailedException.getMessage());

        return new ResponseEntity<>(data, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(EntityUpdateException entityUpdateException){

        IncorrectData data = new IncorrectData();
        data.setInfo(entityUpdateException.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(InvalidDataInputException invalidDataInputException){

        IncorrectData data = new IncorrectData();
        data.setInfo(invalidDataInputException.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchEntityException noSuchEntityException){

        IncorrectData data = new IncorrectData();
        data.setInfo(noSuchEntityException.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
