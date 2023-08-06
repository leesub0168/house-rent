package com.org.houserent.controller.dto.response;

import com.org.houserent.service.dto.HouseSaleContractDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchHouseSaleResponseDto {
    private LocalDateTime sale_contract_date;
    private double building_area;
    private int sale_price;

    @Builder
    public SearchHouseSaleResponseDto(LocalDateTime sale_contract_date, double building_area, int sale_price) {
        this.sale_contract_date = sale_contract_date;
        this.building_area = building_area;
        this.sale_price = sale_price;
    }

    public SearchHouseSaleResponseDto(HouseSaleContractDto houseSaleContractDto) {
        this.sale_contract_date = houseSaleContractDto.getSale_contract_date();
        this.building_area = houseSaleContractDto.getBuilding_area();
        this.sale_price = houseSaleContractDto.getSale_price();
    }
}
