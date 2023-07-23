package com.org.houserent.batch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class juso_road_name_cd {

    @Id
    private String juso_road_name_cd;
    private String road_address_name;
    private String road_address_english_name;
    private String dong_serial_num;
    private String si_do_name;
    private String si_do_english_name;
    private String si_gun_gu_name;
    private String si_gun_gu_english_name;
    private String dong_name;
    private String dong_english_name;
    private String dong_type;
    private String dong_cd;
    private String use_yn;
    private String change_reason;
    private String change_history_info;
    private String notification_date;
    private String cancellation_date;
}