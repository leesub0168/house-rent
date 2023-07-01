package com.org.houserent.repository;

import com.org.houserent.domain.House;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class HouseRepositoryTest {

    @Autowired
    HouseRepository houseRepository;

    public House makeHouse() {
        return House.builder()
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
    }

    @Test
    public void 집_저장_도로명_주소_조회() {
        //given
        House house = makeHouse();

        //when
        houseRepository.save(house);
        Optional<House> findHouse = houseRepository.findHouseByRoadAddress(house.getRoad_name());

        //then
        assertAll(
                () -> assertNotNull(findHouse.get()),
                () -> assertEquals(findHouse.get().getBuilding_main_num(), house.getBuilding_main_num()),
                () -> assertEquals(findHouse.get().getBuilding_sub_num(), house.getBuilding_sub_num()),
                () -> assertEquals(findHouse.get().getBjdong_cd(), house.getBjdong_cd())
        );
    }

    @Test
    public void 집_지번주소_조회() {
        //given
        House house = makeHouse();
        houseRepository.save(house);

        //when
        String searchAddress = house.getDong() + " " + house.getLand_main_num() + "-" + house.getLand_sub_num();
        Optional<House> findHouse = houseRepository.findHouseByLandAddress(searchAddress);

        //then
        assertEquals(house.getBjdong_cd(), findHouse.get().getBjdong_cd());
        assertEquals(house.getCity(), findHouse.get().getCity());
        assertEquals(house.getSgg_cd(), findHouse.get().getSgg_cd());
        assertEquals(house.getZipcode(), findHouse.get().getZipcode());
    }

}