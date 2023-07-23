package com.org.houserent.batch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class juso_land_address_info {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private juso_address_info control_num;
    private String serial_num;
    private String bjd_cd;
    private String si_do_name;
    private String si_gun_gu_name;
    private String bjd_dong_name;
    private String bj_ree_name;
    private String mountain_yn;
    private String land_main_num;
    private String land_sub_num;
    private String represent_yn;
}
