package com.org.houserent.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
public class ApiEntity {
    @JsonProperty("ACC_YEAR")
    private String acc_year;

    @JsonProperty("SGG_CD")
    private String sgg_cd;

    @JsonProperty("SGG_NM")
    private String sgg_nm;

    @JsonProperty("BJDONG_CD")
    private String bjdong_cd;

    @JsonProperty("BJDONG_NM")
    private String bjdong_nm;

    @JsonProperty("LAND_GBN")
    private String land_gbn;

    @JsonProperty("LAND_GBN_NM")
    private String land_gbn_nm;

    @JsonProperty("BOBN")
    private String bobn;

    @JsonProperty("BUBN")
    private String bubn;

    @JsonProperty("FLR_NO")
    private double flr_no;

    @JsonProperty("CNTRCT_DE")
    private String cntrct_de;

    @JsonProperty("RENT_GBN")
    private String rent_gbn;

    @JsonProperty("RENT_AREA")
    private double rent_area;

    @JsonProperty("RENT_GTN")
    private String rent_gtn;

    @JsonProperty("RENT_FEE")
    private String rent_fee;

    @JsonProperty("BLDG_NM")
    private String bldg_nm;

    @JsonProperty("BUILD_YEAR")
    private String build_year;

    @JsonProperty("HOUSE_GBN_NM")
    private String house_gbn_nm;

    @JsonProperty("CNTRCT_PRD")
    private String cntrct_prd;

    @JsonProperty("NEW_RON_SECD")
    private String new_ron_secd;

    @JsonProperty("CNTRCT_UPDT_RQEST_AT")
    private String cntrct_updt_rqest_at;

    @JsonProperty("BEFORE_GRNTY_AMOUNT")
    private String before_grnty_amount;

    @JsonProperty("BEFORE_MT_RENT_CHRGE")
    private String before_mt_rent_chrge;

    @Override
    public String toString() {
        return "ApiEntity{" +
                "acc_year='" + acc_year + '\'' +
                ", sgg_cd='" + sgg_cd + '\'' +
                ", sgg_nm='" + sgg_nm + '\'' +
                ", bjdong_cd='" + bjdong_cd + '\'' +
                ", bjdong_nm='" + bjdong_nm + '\'' +
                ", land_gbn='" + land_gbn + '\'' +
                ", land_gbn_nm='" + land_gbn_nm + '\'' +
                ", bobn='" + bobn + '\'' +
                ", bubn='" + bubn + '\'' +
                ", flr_no=" + flr_no +
                ", cntrct_de='" + cntrct_de + '\'' +
                ", rent_gbn='" + rent_gbn + '\'' +
                ", rent_area=" + rent_area +
                ", rent_gtn='" + rent_gtn + '\'' +
                ", rent_fee='" + rent_fee + '\'' +
                ", bldg_nm='" + bldg_nm + '\'' +
                ", build_year='" + build_year + '\'' +
                ", house_gbn_nm='" + house_gbn_nm + '\'' +
                ", cntrct_prd='" + cntrct_prd + '\'' +
                ", new_ron_secd='" + new_ron_secd + '\'' +
                ", cntrct_updt_rqest_at='" + cntrct_updt_rqest_at + '\'' +
                ", before_grnty_amount='" + before_grnty_amount + '\'' +
                ", before_mt_rent_chrge='" + before_mt_rent_chrge + '\'' +
                '}';
    }
}
