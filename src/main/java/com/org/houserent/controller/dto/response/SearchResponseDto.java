package com.org.houserent.controller.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResponseDto {
    private SearchHouseResponseDto house;
    private List<SearchHouseRentResponseDto> houseRentList;
    private List<SearchHouseSaleResponseDto> houseSaleList;
}
