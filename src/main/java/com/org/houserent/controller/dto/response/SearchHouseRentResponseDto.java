package com.org.houserent.controller.dto.response;

import com.org.houserent.service.dto.HouseRentContractDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchHouseRentResponseDto {
    private LocalDateTime rent_contract_date;
    private String rent_type;
    private double rent_area;
    private int deposit;
    private int rent_fee;

    @Builder
    public SearchHouseRentResponseDto(LocalDateTime rent_contract_date, String rent_type,
                                      double rent_area, int deposit, int rent_fee) {
        this.rent_contract_date = rent_contract_date;
        this.rent_type = rent_type;
        this.rent_area = rent_area;
        this.deposit = deposit;
        this.rent_fee = rent_fee;
    }

    public SearchHouseRentResponseDto(HouseRentContractDto houseRentContractDto) {
        this.rent_contract_date = houseRentContractDto.getRent_contract_date();
        this.rent_type = houseRentContractDto.getRent_type();
        this.rent_area = houseRentContractDto.getRent_area();
        this.deposit = houseRentContractDto.getDeposit();
        this.rent_fee = houseRentContractDto.getRent_fee();
    }
}
