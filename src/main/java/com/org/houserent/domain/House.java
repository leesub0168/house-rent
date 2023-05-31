package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class House {
    @Id @GeneratedValue
    @Column(name = "house_id")
    private Long id;

    private String city;

    private String zipcode;

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
}
