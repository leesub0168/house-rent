package com.org.houserent.util.jusoApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class JusoApiSubDtoResultCode {
    private String errorMessage;
    private int countPerPage;
    private int totalCount;
    private String errorCode;
    private int currentPage;
}
