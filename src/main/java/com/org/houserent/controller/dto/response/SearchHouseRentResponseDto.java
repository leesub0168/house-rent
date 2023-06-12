package com.org.houserent.controller.dto.response;

import lombok.Getter;

@Getter
public class SearchHouseRentResponseDto {
    private String rent_contract_date;
    private String rent_type;
    private String rent_area;
    private int deposit;
    private int rent_fee;
}
