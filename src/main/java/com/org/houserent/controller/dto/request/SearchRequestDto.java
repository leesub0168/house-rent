package com.org.houserent.controller.dto.request;

import lombok.Getter;

import java.util.Optional;

@Getter
public class SearchRequestDto {
    private Long memberId;
    private String searchAddress;
    private boolean roadAddressYn;
}
