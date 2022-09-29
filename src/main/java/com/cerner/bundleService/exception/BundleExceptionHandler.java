package com.cerner.bundleService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BundleExceptionHandler extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgumentException(
            MethodArgumentNotValidException ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check the inputs given");
    }
    public BundleExceptionHandler(){

    }

    public BundleExceptionHandler(String message){
        super(message);
    }
}
