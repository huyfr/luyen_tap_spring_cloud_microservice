/*
package com.example.student_service.exception;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity handleFeignStatusException(FeignException e, HttpServletResponse response) {
        return ResponseEntity.status(e.status()).body("Test error");
    }
}
*/
