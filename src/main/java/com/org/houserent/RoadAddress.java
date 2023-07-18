package com.org.houserent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoadAddress {
    private String sigungu_cd;
    private String rd_sn;
    private String bjdong_sn;
    private String rd_nm;
    private String eng_rd_nm;
    private String sido_nm;
    private String sigungu_nm;
    private String bjdong_gbn;
    private String bjdong_cd;
    private String bjdong_nm;
    private String upper_rd_sn;
    private String upper_rd_nm;
    private String use_yn;
    private String chang_hist_caus_cd;
    private String chang_hist_info;
    private String bjdong_no;
    private String rd_code;

    @Builder
    public RoadAddress(String sigungu_cd, String rd_sn, String bjdong_sn,
                       String rd_nm, String eng_rd_nm, String sido_nm, String sigungu_nm,
                       String bjdong_gbn, String bjdong_cd, String bjdong_nm, String upper_rd_sn,
                       String upper_rd_nm, String use_yn, String chang_hist_caus_cd,
                       String chang_hist_info, String bjdong_no, String rd_code) {
        this.sigungu_cd = sigungu_cd;
        this.rd_sn = rd_sn;
        this.bjdong_sn = bjdong_sn;
        this.rd_nm = rd_nm;
        this.eng_rd_nm = eng_rd_nm;
        this.sido_nm = sido_nm;
        this.sigungu_nm = sigungu_nm;
        this.bjdong_gbn = bjdong_gbn;
        this.bjdong_cd = bjdong_cd;
        this.bjdong_nm = bjdong_nm;
        this.upper_rd_sn = upper_rd_sn;
        this.upper_rd_nm = upper_rd_nm;
        this.use_yn = use_yn;
        this.chang_hist_caus_cd = chang_hist_caus_cd;
        this.chang_hist_info = chang_hist_info;
        this.bjdong_no = bjdong_no;
        this.rd_code = rd_code;
    }
}
