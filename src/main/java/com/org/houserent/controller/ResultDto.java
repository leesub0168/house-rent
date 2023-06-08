package com.org.houserent.controller;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResultDto<T> {
    private HttpStatus status;
    private int status_code;
    private String message;
    private T data;

    @Builder
    public ResultDto(HttpStatus status, String message, T data) {
        this.status = status;
        this.status_code = status.value();
        this.message = message;
        this.data = data;
    }

    public ResultDto(HttpStatus status, String message) {
        this.status = status;
        this.status_code = status.value();
        this.message = message;
    }
}
