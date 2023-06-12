package com.org.houserent.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDto<T> {
    private HttpStatus status;
    private int status_code;
    private String message;
    private T data;

    @Builder
    public ResponseDto(HttpStatus status, String message, T data) {
        this.status = status;
        this.status_code = status.value();
        this.message = message;
        this.data = data;
    }

    public ResponseDto(HttpStatus status, String message) {
        this.status = status;
        this.status_code = status.value();
        this.message = message;
    }
}
