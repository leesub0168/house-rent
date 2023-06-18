package com.org.houserent.service.dto;

import com.org.houserent.domain.HouseSaleContract;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HouseSaleContractDto {
    private Long id;
    private LocalDateTime sale_contract_date;
    private double floor;
    private int sale_price;

    private double building_area;
    private double site_area;

    private String right_gbn;       // 권리 구분
    private String declare_type;    // 신고 구분
    private String declare_estate_agent_address; // 신고한 개업공인중개사 시군구명

    private HouseDto houseDto;

    @Builder
    public HouseSaleContractDto(Long id, LocalDateTime sale_contract_date, double floor, int sale_price,
                                double building_area, double site_area, String right_gbn,
                                String declare_type, String declare_estate_agent_address,
                                HouseDto houseDto) {
        this.id = id;
        this.sale_contract_date = sale_contract_date;
        this.floor = floor;
        this.sale_price = sale_price;
        this.building_area = building_area;
        this.site_area = site_area;
        this.right_gbn = right_gbn;
        this.declare_type = declare_type;
        this.declare_estate_agent_address = declare_estate_agent_address;
        this.houseDto = houseDto;
    }

    public HouseSaleContractDto(HouseSaleContract houseSaleContract) {
        this.id = houseSaleContract.getId();
        this.sale_contract_date = houseSaleContract.getSale_contract_date();
        this.floor = houseSaleContract.getFloor();
        this.sale_price = houseSaleContract.getSale_price();
        this.building_area = houseSaleContract.getBuilding_area();
        this.site_area = houseSaleContract.getSite_area();
        this.right_gbn = houseSaleContract.getRight_gbn();
        this.declare_type = houseSaleContract.getDeclare_type();
        this.declare_estate_agent_address = houseSaleContract.getDeclare_estate_agent_address();
        this.houseDto = new HouseDto(houseSaleContract.getHouse());
    }

    public HouseSaleContract toEntity() {
        return HouseSaleContract.builder()
                .id(getId())
                .building_area(getBuilding_area())
                .site_area(getSite_area())
                .floor(getFloor())
                .sale_price(getSale_price())
                .sale_contract_date(getSale_contract_date())
                .right_gbn(getRight_gbn())
                .declare_type(getDeclare_type())
                .declare_estate_agent_address(getDeclare_estate_agent_address())
                .house(getHouseDto().toEntity())
                .build();
    }
}
