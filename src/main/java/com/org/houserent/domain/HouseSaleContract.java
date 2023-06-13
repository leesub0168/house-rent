package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class HouseSaleContract {
    @Id @GeneratedValue
    @Column(name = "house_sale_contract_id")
    private Long id;

    private double building_area;
    private double site_area;
    private double floors;

    private int sale_price;

    private LocalDateTime sale_date;

    private String declare_type;
    private String declare_estate_agent_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

}
