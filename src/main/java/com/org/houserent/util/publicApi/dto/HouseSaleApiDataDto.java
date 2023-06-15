package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class HouseSaleApiDataDto extends SeoulResponseBaseDto {
    private String BONBEON;
    private String BUBEON;
    private String DEAL_YMD;
    private String OBJ_AMT;
    private double BLDG_AREA;
    private double TOT_AREA;
    private double FLOOR;
    private String RIGHT_GBN;
    private String CNTL_YMD;
    private String HOUSE_TYPE;
    private String REQ_GBN;
    private String RDEALER_LAWDNM;
}
