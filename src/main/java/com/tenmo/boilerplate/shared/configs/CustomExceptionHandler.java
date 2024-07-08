package com.tenmo.boilerplate.shared.configs;


import com.tenmo.boilerplate.shared.exceptions.BusinessException;
import com.tenmo.boilerplate.shared.http.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(new ApiResponse(e.getStatus().value(), e.getMessage()), e.getStatus());
    }
}
