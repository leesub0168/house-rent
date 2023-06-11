package com.org.houserent.controller;

import lombok.Getter;

@Getter
public class SearchHouseRentContractDto {
    private String rent_contract_date;
    private String rent_type;
    private String rent_area;
    private int deposit;
    private int rent_fee;
}
