package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class House {
    @Id @GeneratedValue
    @Column(name = "house_id")
    private Long id;

    private String building_year; // 건축년도
    private String building_name; // 건물명
    private String usage; // 건물용도

    private String sgg_cd; // 자치구 코드
    private String sgg_nm; // 자치구 명

    private String bjdong_cd; // 법정동 코드
    private String bjdong_nm; // 법정동 명

    private String land_type; // 지번 구분

    private String title_num; // 본번
    private String sub_num; // 부번

    private String zipcode;

    private String city;

    private String gu;

    private String dong;

    private String road_name;

    private String building_number;

    private String detail_address;

    @OneToMany(mappedBy = "house")
    private List<HouseRentContract> houseRentContractList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(id, house.id) && Objects.equals(building_year, house.building_year) && Objects.equals(building_name, house.building_name) && Objects.equals(usage, house.usage) && Objects.equals(sgg_cd, house.sgg_cd) && Objects.equals(sgg_nm, house.sgg_nm) && Objects.equals(bjdong_cd, house.bjdong_cd) && Objects.equals(bjdong_nm, house.bjdong_nm) && Objects.equals(land_type, house.land_type) && Objects.equals(title_num, house.title_num) && Objects.equals(sub_num, house.sub_num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, building_year, building_name, usage, sgg_cd, sgg_nm, bjdong_cd, bjdong_nm, land_type, title_num, sub_num);
    }
}
