package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseRentContract {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_rent_contract_seq")
    @SequenceGenerator(name = "house_rent_contract_seq", sequenceName = "house_rent_contract_seq", allocationSize = 1)
    @Column(name = "house_rent_contract_id")
    private Long id;

    private LocalDateTime rent_contract_date;
    private LocalDateTime rent_contract_start_date;
    private LocalDateTime rent_contract_end_date;

    private int deposit;
    private int rent_fee;

    private double rent_area;

    private double floor;

    private String rent_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Builder
    public HouseRentContract(Long id, LocalDateTime rent_contract_date, LocalDateTime rent_contract_start_date,
                             LocalDateTime rent_contract_end_date, int deposit, int rent_fee, double rent_area,
                             double floor, String rent_type, House house) {
        this.id = id;
        this.rent_contract_date = rent_contract_date;
        this.rent_contract_start_date = rent_contract_start_date;
        this.rent_contract_end_date = rent_contract_end_date;
        this.deposit = deposit;
        this.rent_fee = rent_fee;
        this.rent_area = rent_area;
        this.floor = floor;
        this.rent_type = rent_type;
        this.house = house;
    }
}
