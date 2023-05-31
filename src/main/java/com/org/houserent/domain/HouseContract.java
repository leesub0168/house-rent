package com.org.houserent.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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

    private int floor;

    private String rent_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

}
