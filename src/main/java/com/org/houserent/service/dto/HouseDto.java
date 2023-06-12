package com.org.houserent.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HouseDto {
    private String city;
    private String gu;
    private String dong;
    private String road_name;
    private String building_number;
    private String detail_address;
    private String zipcode;

    @Builder
    public HouseDto(String city, String gu, String dong, String road_name, String building_number, String detail_address, String zipcode) {
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.road_name = road_name;
        this.building_number = building_number;
        this.detail_address = detail_address;
        this.zipcode = zipcode;
    }
}
