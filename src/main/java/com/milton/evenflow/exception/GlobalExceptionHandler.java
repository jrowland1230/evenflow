package com.milton.evenflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<String> handleMissingRequestParam(ServerWebInputException ex) {
        String message = "Missing or invalid request parameter: " + ex.getReason();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        //TODO intercept exception and add more details
    }
}
