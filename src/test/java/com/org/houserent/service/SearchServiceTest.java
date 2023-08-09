package com.org.houserent.service;

import com.org.houserent.controller.dto.response.SearchResponseDto;
import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.repository.HouseRentContractRepository;
import com.org.houserent.repository.HouseRepository;
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

    @Test
    public void 집_메인_검색() throws Exception {
        //given
        House house = getHouse();
        houseRepository.save(house);

        HouseRentContract houseRentContract = getHouseRentContract(house);
        houseRentContractRepository.save(houseRentContract);

        String keyword = "방화대로7가길 35";

        SearchResponseDto searchResponseDto = searchService.searchAddress("setsd", keyword, true);

        assertNotNull(searchResponseDto);

        assertAll(
                () -> assertNotNull(searchResponseDto),
                () -> assertEquals("공항동", searchResponseDto.getHouse().getDong()),
                () -> assertEquals(1, searchResponseDto.getHouseRentList().size())
        );
    }

}