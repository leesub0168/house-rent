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
public class HouseSaleContract {
    @Id @GeneratedValue
    @Column(name = "house_sale_contract_id")
    private Long id;

    private double building_area;   // 건물 면적(m2)
    private double site_area;       // 토지 면적(m2)
    private double floor;           // 층

    private int sale_price;         // 물건 금액(만원)

    private LocalDateTime sale_contract_date;    // 계약 일자

    private String right_gbn;       // 권리 구분
    private String declare_type;    // 신고 구분
    private String declare_estate_agent_address; // 신고한 개업공인중개사 시군구명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Builder
    public HouseSaleContract(Long id, double building_area, double site_area, double floor,
                             int sale_price, LocalDateTime sale_contract_date,
                             String right_gbn, String declare_type, String declare_estate_agent_address,
                             House house) {
        this.id = id;
        this.building_area = building_area;
        this.site_area = site_area;
        this.floor = floor;
        this.sale_price = sale_price;
        this.sale_contract_date = sale_contract_date;
        this.right_gbn = right_gbn;
        this.declare_type = declare_type;
        this.declare_estate_agent_address = declare_estate_agent_address;
        this.house = house;
    }
}
