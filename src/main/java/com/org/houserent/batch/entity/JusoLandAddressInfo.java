package com.org.houserent.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@IdClass(JusoLandAddressId.class)
@Table(name = "juso_land_address_info")
public class JusoLandAddressInfo {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_num")
    private JusoAddressInfo jusoAddressInfo;

    @Id
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
