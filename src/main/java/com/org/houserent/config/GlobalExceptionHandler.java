package com.org.houserent.config;

import com.org.houserent.controller.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseDto handleAuthenticationException(AuthenticationException e) {
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();

    }
}
