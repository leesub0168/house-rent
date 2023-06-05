package com.org.houserent.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter
@Data
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class ApiEntity {
    private String ACC_YEAR;

    private String SGG_CD;

    private String SGG_NM;

    private String BJDONG_CD;

    private String BJDONG_NM;

    private String LAND_GBN;

    private String LAND_GBN_NM;

    private String BOBN;

    private String BUBN;

    private double FLR_NO;

    private String CNTRCT_DE;

    private String RENT_GBN;

    private double RENT_AREA;

    private String RENT_GTN;

    private String RENT_FEE;

    private String BLDG_NM;

    private String BUILD_YEAR;

    private String HOUSE_GBN_NM;

    private String CNTRCT_PRD;

    private String NEW_RON_SECD;

    private String CNTRCT_UPDT_RQEST_AT;

    private String BEFORE_GRNTY_AMOUNT;

    private String BEFORE_MT_RENT_CHRGE;

    @Override
    public String toString() {
        return "ApiEntity{" +
                "ACC_YEAR='" + ACC_YEAR + '\'' +
                ", SGG_CD='" + SGG_CD + '\'' +
                ", SGG_NM='" + SGG_NM + '\'' +
                ", BJDONG_CD='" + BJDONG_CD + '\'' +
                ", BJDONG_NM='" + BJDONG_NM + '\'' +
                ", LAND_GBN='" + LAND_GBN + '\'' +
                ", LAND_GBN_NM='" + LAND_GBN_NM + '\'' +
                ", BOBN='" + BOBN + '\'' +
                ", BUBN='" + BUBN + '\'' +
                ", FLR_NO=" + FLR_NO +
                ", CNTRCT_DE='" + CNTRCT_DE + '\'' +
                ", RENT_GBN='" + RENT_GBN + '\'' +
                ", RENT_AREA=" + RENT_AREA +
                ", RENT_GTN='" + RENT_GTN + '\'' +
                ", RENT_FEE='" + RENT_FEE + '\'' +
                ", BLDG_NM='" + BLDG_NM + '\'' +
                ", BUILD_YEAR='" + BUILD_YEAR + '\'' +
                ", HOUSE_GBN_NM='" + HOUSE_GBN_NM + '\'' +
                ", CNTRCT_PRD='" + CNTRCT_PRD + '\'' +
                ", NEW_RON_SECD='" + NEW_RON_SECD + '\'' +
                ", CNTRCT_UPDT_RQEST_AT='" + CNTRCT_UPDT_RQEST_AT + '\'' +
                ", BEFORE_GRNTY_AMOUNT='" + BEFORE_GRNTY_AMOUNT + '\'' +
                ", BEFORE_MT_RENT_CHRGE='" + BEFORE_MT_RENT_CHRGE + '\'' +
                '}';
    }
}
