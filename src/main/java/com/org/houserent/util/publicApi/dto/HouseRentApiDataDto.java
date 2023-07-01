package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseRentContractDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class HouseRentApiDataDto extends SeoulResponseBaseDto {

    private String BOBN;
    private String BUBN;
    private double FLR_NO;
    private String CNTRCT_DE;
    private String RENT_GBN;                // 전월세 구분
    private double RENT_AREA;               // 임대 면적 (m2)
    private int RENT_GTN;                // 보증금 (만원)
    private int RENT_FEE;                // 임대료 (만원)
    private String HOUSE_GBN_NM;            // 건물용도
    private String CNTRCT_PRD;              // 계약 기간
    private String NEW_RON_SECD;            // 신규 갱신 여부
    private String CNTRCT_UPDT_RQEST_AT;    // 계약 갱신권 사용 여부
    private String BEFORE_GRNTY_AMOUNT;     // 종전 보증금
    private String BEFORE_MT_RENT_CHRGE;    // 종전 임대료

    public HouseRentContractDto toHouseRentContractDto(HouseDto houseDto) {
        return HouseRentContractDto.builder()
                .deposit(getRENT_GTN())
                .rent_type(getRENT_GBN())
                .rent_area(getRENT_AREA())
                .rent_contract_date(dealYmdToLocalDateTime())
                .houseDto(houseDto)
                .build();
    }

    public LocalDateTime dealYmdToLocalDateTime() {
        int year = Integer.parseInt(getCNTRCT_DE().substring(0, 4));
        int month = Integer.parseInt(getCNTRCT_DE().substring(4, 6));
        int day = Integer.parseInt(getCNTRCT_DE().substring(6, 8));

        return LocalDateTime.of(year, month, day, 0, 0);
    }
}
