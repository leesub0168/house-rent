package com.org.houserent.exception;

import com.org.houserent.controller.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleDuplicateMemberException(Exception e) {
        log.error("DuplicateMemberException ", e);
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleWrongPasswordException(Exception e) {
        log.error("WrongPasswordException ", e);
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NonExistMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleNonExistMemberException(Exception e) {
        log.error("NonExistMemberException", e);
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseDto handleAuthenticationException(Exception e) {
        log.error(e.getClass().getSimpleName(), e);
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();

    }
}
