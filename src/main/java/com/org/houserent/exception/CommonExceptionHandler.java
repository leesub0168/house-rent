package com.org.houserent.exception;

import com.org.houserent.controller.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto handleDuplicateMemberException(Exception e) {
        log.error("DuplicateMemberException ", e);
        return ResultDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto handleWrongPasswordException(Exception e) {
        log.error("WrongPasswordException ", e);
        return ResultDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }
}
