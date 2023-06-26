package com.org.houserent.controller.dto.response;

import com.org.houserent.service.dto.HouseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchHouseResponseDto {
    private String city;
    private String gu;
    private String road_name;
    private String building_main_num;
    private String building_sub_num;
    private String dong;
    private int land_main_num;
    private int land_sub_num;
    private String zipcode;

    @Builder
    public SearchHouseResponseDto(String city, String gu, String road_name,
                                  String building_main_num, String building_sub_num,
                                  String dong, int land_main_num, int land_sub_num,
                                  String zipcode) {
        this.city = city;
        this.gu = gu;
        this.road_name = road_name;
        this.building_main_num = building_main_num;
        this.building_sub_num = building_sub_num;
        this.dong = dong;
        this.land_main_num = land_main_num;
        this.land_sub_num = land_sub_num;
        this.zipcode = zipcode;
    }

    public SearchHouseResponseDto(HouseDto houseDto) {
        this.city = houseDto.getCity();
        this.gu = houseDto.getGu();
        this.road_name = houseDto.getRoad_name();
        this.building_main_num = houseDto.getBuilding_main_num();
        this.building_sub_num = houseDto.getBuilding_sub_num();
        this.dong = houseDto.getDong();
        this.land_main_num = houseDto.getLand_main_num();
        this.land_sub_num = houseDto.getLand_sub_num();
        this.zipcode = houseDto.getZipcode();
    }
}
