package com.org.houserent.service;

import com.org.houserent.controller.dto.response.SearchResponseDto;
import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.domain.HouseSaleContract;
import com.org.houserent.repository.HouseRentContractRepository;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.repository.HouseSaleContractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SearchServiceTest {

    @Autowired
    SearchService searchService;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    HouseRentContractRepository houseRentContractRepository;

    @Autowired
    HouseSaleContractRepository houseSaleContractRepository;


    public House getHouse() {
        return House.builder()
                .sgg_cd("11500")
                .sgg_nm("강서구")
                .city("서울특별시")
                .dong("공항동")
                .gu("강서구")
                .zipcode("07625")
                .road_name("방화대로7가길")
                .building_main_num("35")
                .building_sub_num("0")
                .bjdong_cd("10800")
                .land_main_num(658)
                .land_sub_num(5)
                .detail_address("현대홈타운")
                .build();
    }

    public HouseRentContract getHouseRentContract(House house) {
        return HouseRentContract.builder()
                .rent_contract_date(LocalDateTime.now())
                .deposit(16500)
                .rent_fee(10)
                .rent_area(20.05)
                .rent_type("월세")
                .house(house)
                .build();
    }

    public HouseSaleContract getHouseSaleContract(House house) {
        return HouseSaleContract.builder()
                .sale_contract_date(LocalDateTime.now())
                .floor(3.0)
                .sale_price(24500)
                .building_area(35.34)
                .site_area(25.55)
                .right_gbn("")
                .declare_type("직거래")
                .declare_estate_agent_address("")
                .house(house)
                .build();

    }

    @Test
    public void 집_메인_검색() throws Exception {
        //given
        House house = getHouse();
        houseRepository.save(house);

        HouseRentContract houseRentContract = getHouseRentContract(house);
        houseRentContractRepository.save(houseRentContract);

        HouseSaleContract houseSaleContract = getHouseSaleContract(house);
        houseSaleContractRepository.save(houseSaleContract);


        String keyword = "방화대로7가길 35";

        SearchResponseDto searchResponseDto = searchService.searchAddress("setsd", keyword, true);

        assertNotNull(searchResponseDto);

        assertAll(
                () -> assertNotNull(searchResponseDto),
                () -> assertEquals("공항동", searchResponseDto.getHouse().getDong()),
                () -> assertEquals(16500, searchResponseDto.getHouseRentList().get(0).getDeposit()),
                () -> assertEquals(35.34, searchResponseDto.getHouseSaleList().get(0).getBuilding_area())
        );
    }

}