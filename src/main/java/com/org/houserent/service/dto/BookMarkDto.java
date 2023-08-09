package com.org.houserent.service.dto;

import com.org.houserent.domain.BookMark;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookMarkDto {
    private Long bookMarkId;
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
    public BookMarkDto(Long bookMarkId, String city, String gu, String road_name,
                       String building_main_num, String building_sub_num,
                       String dong, int land_main_num, int land_sub_num,
                       String zipcode) {
        this.bookMarkId = bookMarkId;
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

    public BookMarkDto(BookMark bookMark) {
        this.bookMarkId = bookMark.getId();
        this.city = bookMark.getHouse().getCity();
        this.gu = bookMark.getHouse().getGu();
        this.road_name = bookMark.getHouse().getRoad_name();
        this.building_main_num = bookMark.getHouse().getBuilding_main_num();
        this.building_sub_num = bookMark.getHouse().getBuilding_sub_num();
        this.dong = bookMark.getHouse().getDong();
        this.land_main_num = bookMark.getHouse().getLand_main_num();
        this.land_sub_num = bookMark.getHouse().getLand_sub_num();
        this.zipcode = bookMark.getHouse().getZipcode();
    }
}
