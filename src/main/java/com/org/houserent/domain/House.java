package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@Table(name = "House")
public class House {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_seq")
    @SequenceGenerator(name = "house_seq", sequenceName = "house_seq", allocationSize = 1)
    @Column(name = "house_id")
    private Long id;

    private String building_year; // 건축년도
    private String building_name; // 건물명
    private String building_usage; // 건물용도

    private String sgg_cd; // 자치구 코드
    private String sgg_nm; // 자치구 명

    private String bjdong_cd; // 법정동 코드
    private String bjdong_nm; // 법정동 명

    private String building_main_num; // 도로명 - 건물 본번
    private String building_sub_num; // 도로명 - 건물 부번

    private int land_main_num;   // 지번 주소 - 본번
    private int land_sub_num;    // 지번 주소 - 부번

    private String zipcode;

    private String city;

    private String gu;

    private String dong;

    private String road_name;

    private String detail_address;

    /*@OneToMany(mappedBy = "house")
    private List<HouseRentContract> houseRentContractList = new ArrayList<>();

    @OneToMany(mappedBy = "house")
    private List<HouseSaleContract> houseSaleContractList = new ArrayList<>();*/

    public House() {
    }

    @Builder
    public House(Long id, String building_year, String building_name, String building_usage,
                 String sgg_cd, String sgg_nm, String bjdong_cd, String bjdong_nm,
                 String building_main_num, String building_sub_num, String zipcode,
                 int land_main_num, int land_sub_num, String city, String gu,
                 String dong, String road_name, String detail_address) {
        this.id = id;
        this.building_year = building_year;
        this.building_name = building_name;
        this.building_usage = building_usage;
        this.sgg_cd = sgg_cd;
        this.sgg_nm = sgg_nm;
        this.bjdong_cd = bjdong_cd;
        this.bjdong_nm = bjdong_nm;
        this.building_main_num = building_main_num;
        this.building_sub_num = building_sub_num;
        this.land_main_num = land_main_num;
        this.land_sub_num = land_sub_num;
        this.zipcode = zipcode;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.road_name = road_name;
        this.detail_address = detail_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(id, house.id) && Objects.equals(building_year, house.building_year)
                && Objects.equals(building_name, house.building_name) && Objects.equals(building_usage, house.building_usage)
                && Objects.equals(sgg_cd, house.sgg_cd) && Objects.equals(sgg_nm, house.sgg_nm)
                && Objects.equals(bjdong_cd, house.bjdong_cd) && Objects.equals(bjdong_nm, house.bjdong_nm)
                && Objects.equals(building_main_num, house.building_main_num) && Objects.equals(building_sub_num, house.building_sub_num)
                && Objects.equals(land_main_num, house.land_main_num) && Objects.equals(land_sub_num, house.land_sub_num)
                && Objects.equals(zipcode, house.zipcode) && Objects.equals(city, house.city) && Objects.equals(gu, house.gu)
                && Objects.equals(dong, house.dong) && Objects.equals(road_name, house.road_name)
                && Objects.equals(detail_address, house.detail_address
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, building_year, building_name, building_usage,
                sgg_cd, sgg_nm, bjdong_cd, bjdong_nm, building_main_num, building_sub_num,
                land_main_num, land_sub_num, zipcode, city, gu, dong, road_name, detail_address
        );
    }
}
