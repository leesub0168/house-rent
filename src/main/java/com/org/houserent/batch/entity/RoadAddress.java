package com.org.houserent.batch.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoadAddress {
    private String sigungu_cd;
    private String sigungu_nm;
    private String bjdong_cd;
    private String land_gbn;
    private String land_gbn_nm;
    private String building_name;
    private String building_main_num;
    private String building_sub_num;
    private String building_usage;

    @Builder
    public RoadAddress(String sigungu_cd, String sigungu_nm, String bjdong_cd,
                       String land_gbn, String land_gbn_nm, String building_name,
                       String building_main_num, String building_sub_num, String building_usage) {
        this.sigungu_cd = sigungu_cd;
        this.sigungu_nm = sigungu_nm;
        this.bjdong_cd = bjdong_cd;
        this.land_gbn = land_gbn;
        this.land_gbn_nm = land_gbn_nm;
        this.building_name = building_name;
        this.building_main_num = building_main_num;
        this.building_sub_num = building_sub_num;
        this.building_usage = building_usage;
    }
}
