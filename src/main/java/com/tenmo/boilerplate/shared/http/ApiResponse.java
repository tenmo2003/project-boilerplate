package com.tenmo.boilerplate.shared.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String message;
    private Object data;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message, Object data) {
        this.code = status.value();
        this.message = message;
        this.data = data;
    }
}
