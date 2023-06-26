package com.org.houserent.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponseDto {
    private SearchHouseResponseDto house;
    private List<SearchHouseRentResponseDto> houseRentList;
    private List<SearchHouseSaleResponseDto> houseSaleList;
}
