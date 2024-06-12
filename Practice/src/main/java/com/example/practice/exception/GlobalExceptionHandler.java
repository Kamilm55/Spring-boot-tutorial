package com.example.practice.exception;

import com.example.practice.response.base.ExceptionResponseMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ExceptionResponseMessages> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(
//                new ExceptionResponseMessages(ex.getClass().getName(), ex.getMessage(), HttpStatus.NOT_FOUND) ,
//                HttpStatus.NOT_FOUND
//        );
//    }


    // For unhandled exceptions:
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponseMessages> generalExceptionHandler(Exception ex){
        System.out.println("For unhandled exceptions");
        System.out.println(ex.getClass());
        return new ResponseEntity<>(
                new ExceptionResponseMessages(ex.getClass().getName(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
