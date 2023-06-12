package com.org.houserent.controller.dto.response;

import lombok.Getter;

@Getter
public class SearchHouseSaleResponseDto {
    private String sale_contract_date;
    private double building_area;
    private int sale_price;
}
