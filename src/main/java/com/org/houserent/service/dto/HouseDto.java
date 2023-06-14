package com.org.houserent.service.dto;

import com.org.houserent.domain.House;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HouseDto {
    private Long id;
    private String city;
    private String gu;
    private String dong;
    private String road_name;
    private String building_main_num;
    private String building_sub_num;
    private String land_main_num;
    private String land_sub_num;
    private String detail_address;
    private String zipcode;
    private String sgg_cd;      // 자치구 코드
    private String bjdong_cd;   // 법정동 코드

    @Builder
    public HouseDto(Long id, String city, String gu, String dong, String road_name,
                    String building_main_num, String building_sub_num,
                    String land_main_num, String land_sub_num, String detail_address,
                    String zipcode, String sgg_cd, String bjdong_cd) {
        this.id = id;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.road_name = road_name;
        this.building_main_num = building_main_num;
        this.building_sub_num = building_sub_num;
        this.land_main_num = land_main_num;
        this.land_sub_num = land_sub_num;
        this.detail_address = detail_address;
        this.zipcode = zipcode;
        this.sgg_cd = sgg_cd;
        this.bjdong_cd = bjdong_cd;
    }

    public HouseDto(House house) {
        this.id = house.getId();
        this.city = house.getCity();
        this.gu = house.getGu();
        this.dong = house.getDong();
        this.road_name = house.getRoad_name();
        this.building_main_num = house.getBuilding_main_num();
        this.building_sub_num = house.getBuilding_sub_num();
        this.land_main_num = house.getLand_main_num();
        this.land_sub_num = house.getLand_sub_num();
        this.detail_address = house.getDetail_address();
        this.zipcode = house.getZipcode();
        this.sgg_cd = house.getSgg_cd();
        this.bjdong_cd = house.getBjdong_cd();

    }
}
