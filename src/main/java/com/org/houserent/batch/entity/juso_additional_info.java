package com.org.houserent.batch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class juso_additional_info {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private juso_address_info control_num;
    private String administrative_dong_cd;
    private String administrative_name;
    private String zipcode;
    private String zipcode_serial_num;
    private String many_delivery_name;
    private String building_register_name;
    private String si_gun_gu_building_name;
    private String apartment_house_yn;
}
