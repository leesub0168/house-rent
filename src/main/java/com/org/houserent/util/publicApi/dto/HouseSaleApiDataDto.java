package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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

    public HouseSaleContractDto toHouseSaleContractDto(HouseDto houseDto) {
        return HouseSaleContractDto.builder()
                .sale_contract_date(dealYmdToLocalDateTime())
                .floor(getFLOOR())
                .sale_price(Integer.parseInt(getOBJ_AMT()))
                .building_area(getBLDG_AREA())
                .site_area(getTOT_AREA())
                .right_gbn(getRIGHT_GBN())
                .declare_type(getREQ_GBN())
                .declare_estate_agent_address(getRDEALER_LAWDNM())
                .houseDto(houseDto)
                .build();
    }

    public LocalDateTime dealYmdToLocalDateTime() {
        int year = Integer.parseInt(getDEAL_YMD().substring(0, 4));
        int month = Integer.parseInt(getDEAL_YMD().substring(4, 6));
        int day = Integer.parseInt(getDEAL_YMD().substring(6, 8));

        return LocalDateTime.of(year, month, day, 0, 0);
    }
}
