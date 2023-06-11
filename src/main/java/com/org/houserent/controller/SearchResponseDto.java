package com.org.houserent.controller;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResponseDto {
    private SearchHouseResponseDto house;
    private List<SearchHouseRentContractDto> houseRentList;
    private List<SearchHouseSaleContractDto> houseSaleList;
}
