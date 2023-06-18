package com.org.houserent.service.dto;

import com.org.houserent.domain.HouseRentContract;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HouseRentContractDto {
    private Long id;

    private LocalDateTime rent_contract_date;

    private int deposit;
    private int rent_fee;

    private double rent_area;

    private String rent_type;

    private HouseDto houseDto;

    @Builder
    public HouseRentContractDto(Long id, LocalDateTime rent_contract_date, int deposit,
                                int rent_fee, double rent_area, String rent_type,
                                HouseDto houseDto) {
        this.id = id;
        this.rent_contract_date = rent_contract_date;
        this.deposit = deposit;
        this.rent_fee = rent_fee;
        this.rent_area = rent_area;
        this.rent_type = rent_type;
        this.houseDto = houseDto;
    }

    public HouseRentContractDto(HouseRentContract houseRentContract) {
        this.id = houseRentContract.getId();
        this.rent_contract_date = houseRentContract.getRent_contract_date();
        this.deposit = houseRentContract.getDeposit();
        this.rent_fee = houseRentContract.getRent_fee();
        this.rent_area = houseRentContract.getRent_area();
        this.rent_type = houseRentContract.getRent_type();
        this.houseDto = new HouseDto(houseRentContract.getHouse());
    }

    public HouseRentContract toEntity() {
        return HouseRentContract.builder()
                .id(getId())
                .rent_contract_date(getRent_contract_date())
                .deposit(getDeposit())
                .rent_fee(getRent_fee())
                .rent_area(getRent_area())
                .rent_type(getRent_type())
                .house(getHouseDto().toEntity())
                .build();
    }
}
