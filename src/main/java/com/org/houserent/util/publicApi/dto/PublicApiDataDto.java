package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class PublicApiDataDto extends SeoulResponseBaseDto {

    private String BOBN;

    private String BUBN;

    private double FLR_NO;

    private String CNTRCT_DE;

    private String RENT_GBN;

    private double RENT_AREA;

    private String RENT_GTN;

    private String RENT_FEE;

    private String HOUSE_GBN_NM;

    private String CNTRCT_PRD;

    private String NEW_RON_SECD;

    private String CNTRCT_UPDT_RQEST_AT;

    private String BEFORE_GRNTY_AMOUNT;

    private String BEFORE_MT_RENT_CHRGE;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PublicApiDataEntity{");
        sb.append("ACC_YEAR='").append(getACC_YEAR()).append('\'');
        sb.append(", SGG_CD='").append(getSGG_CD()).append('\'');
        sb.append(", SGG_NM='").append(getSGG_NM()).append('\'');
        sb.append(", BJDONG_CD='").append(getBJDONG_CD()).append('\'');
        sb.append(", BJDONG_NM='").append(getBJDONG_NM()).append('\'');
        sb.append(", LAND_GBN='").append(getLAND_GBN()).append('\'');
        sb.append(", LAND_GBN_NM='").append(getLAND_GBN_NM()).append('\'');
        sb.append(", BLDG_NM='").append(getBLDG_NM()).append('\'');
        sb.append(", BUILD_YEAR='").append(getBUILD_YEAR()).append('\'');
        sb.append(", BOBN='").append(BOBN).append('\'');
        sb.append(", BUBN='").append(BUBN).append('\'');
        sb.append(", FLR_NO=").append(FLR_NO);
        sb.append(", CNTRCT_DE='").append(CNTRCT_DE).append('\'');
        sb.append(", RENT_GBN='").append(RENT_GBN).append('\'');
        sb.append(", RENT_AREA=").append(RENT_AREA);
        sb.append(", RENT_GTN='").append(RENT_GTN).append('\'');
        sb.append(", RENT_FEE='").append(RENT_FEE).append('\'');
        sb.append(", HOUSE_GBN_NM='").append(HOUSE_GBN_NM).append('\'');
        sb.append(", CNTRCT_PRD='").append(CNTRCT_PRD).append('\'');
        sb.append(", NEW_RON_SECD='").append(NEW_RON_SECD).append('\'');
        sb.append(", CNTRCT_UPDT_RQEST_AT='").append(CNTRCT_UPDT_RQEST_AT).append('\'');
        sb.append(", BEFORE_GRNTY_AMOUNT='").append(BEFORE_GRNTY_AMOUNT).append('\'');
        sb.append(", BEFORE_MT_RENT_CHRGE='").append(BEFORE_MT_RENT_CHRGE).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
