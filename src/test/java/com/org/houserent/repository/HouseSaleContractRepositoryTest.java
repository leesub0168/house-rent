package com.org.houserent.repository;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseSaleContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HouseSaleContractRepositoryTest {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    HouseSaleContractRepository houseSaleContractRepository;

    public HouseSaleContract makeHouseSaleContract() {
        House house = House.builder()
                .bjdong_cd("10800")
                .building_main_num("35")
                .building_sub_num("0")
                .city("서울특별시")
                .detail_address("현대홈타운")
                .dong("공항동")
                .gu("강서구")
                .land_main_num(658)
                .land_sub_num(5)
                .road_name("방화대로7가길")
                .sgg_cd("11500")
                .zipcode("07625")
                .build();

        HouseSaleContract houseSaleContract = HouseSaleContract.builder()
                .sale_contract_date(LocalDateTime.now())
                .sale_price(22500)
                .building_area(35.47)
                .site_area(21.24)
                .right_gbn("")
                .floor(3.0)
                .declare_type("직거래")
                .declare_estate_agent_address("")
                .house(house)
                .build();
        return houseSaleContract;
    }

    @Test
    @Transactional
    public void 실거래_계약_저장() throws Exception {
        //given
        HouseSaleContract houseSaleContract = makeHouseSaleContract();
        houseRepository.saveHouse(houseSaleContract.getHouse());


        //when
        houseSaleContractRepository.saveHouseSaleContract(houseSaleContract);
        House house = houseRepository.findHouseByRoadAddress("방화대로7가길 35");
        List<HouseSaleContract> houseSaleContractByHouse = houseSaleContractRepository.findHouseSaleContractByHouse(house.getId());

        //then
        assertNotNull(house);
        assertEquals(1, houseSaleContractByHouse.size());
    }

    @Test
    @Transactional
    public void 실거래_계약_조회() throws Exception {
        //given
        HouseSaleContract houseSaleContract = makeHouseSaleContract();
        houseRepository.saveHouse(houseSaleContract.getHouse());
        houseSaleContractRepository.saveHouseSaleContract(houseSaleContract);
        House house = houseRepository.findHouseByRoadAddress("방화대로7가길 35");

        //when
        List<HouseSaleContract> houseSaleContractByHouse = houseSaleContractRepository.findHouseSaleContractByHouse(house.getId());

        //then
        assertEquals(1, houseSaleContractByHouse.size());
    }
}