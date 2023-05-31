package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseContract {

    @Id @GeneratedValue
    @Column(name = "house_contract_id")
    private Long id;

    private LocalDateTime contract_date;
    private LocalDateTime contract_start_date;
    private LocalDateTime contract_end_date;

    private int deposit;
    private int rent_fee;

    private double rent_area;

    private int floors;

    private String rent_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    public HouseContract(House house, LocalDateTime contract_date, LocalDateTime contract_start_date, LocalDateTime contract_end_date,
        int deposit, int rent_fee, int floors, String rent_type, int rent_area) {
        this.house = house;
        this.contract_date = contract_date;
        this.contract_start_date = contract_start_date;
        this.contract_end_date = contract_end_date;
        this.deposit = deposit;
        this.rent_fee = rent_fee;
        this.floors = floors;
        this.rent_type = rent_type;
        this.rent_area = rent_area;
    }


    public static HouseContract createHouseContract(House house, LocalDateTime contract_date, LocalDateTime contract_start_date, LocalDateTime contract_end_date,
        int deposit, int rent_fee, int floors, String rent_type, int rent_area) {

        HouseContract houseContract = new HouseContract(house, contract_date, contract_start_date, contract_end_date, deposit, rent_fee, floors, rent_type, rent_area);

        return houseContract;
    }
}
