package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public abstract class SeoulResponseBaseDto {
    private String ACC_YEAR;
    private String SGG_CD;
    private String SGG_NM;
    private String BJDONG_CD;
    private String BJDONG_NM;
    private String LAND_GBN;
    private String LAND_GBN_NM;
    private String BLDG_NM;
    private String BUILD_YEAR;
}
