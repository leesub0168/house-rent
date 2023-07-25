package com.org.houserent.batch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "juso_address_info")
public class JusoAddressInfo {
    @Id
    private String control_num;
    private String basement_yn;
    private String building_main_num;
    private String building_sub_num;
    private String base_area_num;
    private String change_reason_cd;
    private String notification_date;
    private String before_change_road_address;
    private String detail_address_yn;

    @ManyToOne(fetch = FetchType.LAZY)
    private JusoRoadNameCd JusoRoadNameCd;
}
