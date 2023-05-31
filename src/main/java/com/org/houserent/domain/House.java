package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "house")
    private List<HouseContract> houseContractList = new ArrayList<>();

    public House(String building_year, String building_name, String usage, String sgg_cd, String sgg_nm,
                 String bjdong_cd, String bjdong_nm) {
        this.building_year = building_year;
        this.building_name = building_name;
        this.usage = usage;
        this.sgg_cd = sgg_cd;
        this.sgg_nm = sgg_nm;
        this.bjdong_cd = bjdong_cd;
        this.bjdong_nm = bjdong_nm;
    }

    public House(String building_year, String building_name, String usage, String sgg_cd, String sgg_nm,
                 String bjdong_cd, String bjdong_nm, String land_type) {
        this.building_year = building_year;
        this.building_name = building_name;
        this.usage = usage;
        this.sgg_cd = sgg_cd;
        this.sgg_nm = sgg_nm;
        this.bjdong_cd = bjdong_cd;
        this.bjdong_nm = bjdong_nm;
        this.land_type = land_type;
    }

    public House(String building_year, String building_name, String usage, String sgg_cd, String sgg_nm,
                 String bjdong_cd, String bjdong_nm, String land_type, String title_num, String sub_num) {
        this.building_year = building_year;
        this.building_name = building_name;
        this.usage = usage;
        this.sgg_cd = sgg_cd;
        this.sgg_nm = sgg_nm;
        this.bjdong_cd = bjdong_cd;
        this.bjdong_nm = bjdong_nm;
        this.land_type = land_type;
        this.title_num = title_num;
        this.sub_num = sub_num;
    }

    public static House createHouse(String building_year, String building_name, String usage, String sgg_cd, String sgg_nm,
                                    String bjdong_cd, String bjdong_nm) {
        House house = new House(building_year, building_name, usage, sgg_cd, sgg_nm, bjdong_cd, bjdong_nm);

        return house;
    }
}
