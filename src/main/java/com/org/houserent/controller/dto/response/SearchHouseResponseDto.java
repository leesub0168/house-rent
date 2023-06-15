package com.org.houserent.controller.dto.response;

import lombok.Getter;

@Getter
public class SearchHouseResponseDto {
    private String city;
    private String gu;
    private String road_name;
    private String building_main_num;
    private String building_sub_num;
    private String dong;
    private String land_main_num;
    private String land_sub_num;
    private String zipcode;
}
