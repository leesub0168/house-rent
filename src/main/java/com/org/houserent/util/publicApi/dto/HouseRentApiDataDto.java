package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class HouseRentApiDataDto extends SeoulResponseBaseDto {

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
}
