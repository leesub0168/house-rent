package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class HouseRentContract {

    @Id @GeneratedValue
    @Column(name = "house_rent_contract_id")
    private Long id;

    private LocalDateTime contract_date;
    private LocalDateTime contract_start_date;
    private LocalDateTime contract_end_date;

    private int deposit;
    private int rent_fee;

    private double rent_area;

    private double floors;

    private String rent_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

}
