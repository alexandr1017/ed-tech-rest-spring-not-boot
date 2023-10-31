package com.alexandr1017.edtechschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleException(ItemNotFoundException e) {
        ItemNotFoundException response = new ItemNotFoundException(e.getMessage());
        return new ResponseEntity<>(response.getMessage(), HttpStatus.NOT_FOUND);
    }
}

