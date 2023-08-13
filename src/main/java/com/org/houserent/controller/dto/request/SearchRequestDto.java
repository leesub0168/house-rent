package com.org.houserent.controller.dto.request;

import lombok.Getter;

import java.util.Optional;

@Getter
public class SearchRequestDto {
    private String userId;
    private String searchAddress;
    private boolean roadAddressYn;
}
