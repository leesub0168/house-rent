package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseRentContractDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HouseRentContractServiceTest {
    @Autowired
    HouseRepository houseRepository;

    @Autowired
    HouseRentContractService houseRentContractService;


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

    public HouseRentContractDto makeHouseRentContractDto(House house) {
        return HouseRentContractDto.builder()
                .rent_contract_date(LocalDateTime.now())
                .deposit(16000)
                .rent_fee(0)
                .rent_area(18.84)
                .rent_type("전세")
                .houseDto(new HouseDto(house))
                .build();
    }

    @Test
    @Transactional
    public void 전월세_계약_정보_저장() throws Exception {
        //given
        House house = makeHouse();
        HouseRentContractDto houseRentContractDto = makeHouseRentContractDto(house);

        houseRepository.saveHouse(house);

        //when
        Long id = houseRentContractService.saveHouseRentContract(houseRentContractDto);

        //then
        assertNotNull(id);
    }

    @Test
    @Transactional
    public void 전월세_계약_정보_아이디로_조회() throws Exception {
        //given
        House house = makeHouse();
        HouseRentContractDto houseRentContractDto = makeHouseRentContractDto(house);

        houseRepository.saveHouse(house);

        Long id = houseRentContractService.saveHouseRentContract(houseRentContractDto);

        //when
        HouseRentContractDto houseRentContractById = houseRentContractService.findHouseRentContractById(id);

        //then
        assertAll(
                () -> assertNotNull(houseRentContractById),
                () -> assertEquals(houseRentContractById.getRent_contract_date(), houseRentContractDto.getRent_contract_date()),
                () -> assertEquals(houseRentContractById.getDeposit(), houseRentContractDto.getDeposit()),
                () -> assertEquals(houseRentContractById.getRent_fee(), houseRentContractDto.getRent_fee()),
                () -> assertEquals(houseRentContractById.getHouseDto().getHouse_id(), houseRentContractDto.getHouseDto().getHouse_id())
        );
    }

    @Test
    @Transactional
    public void 전월세_계약_정보_집정보로_조회(@Autowired HouseService houseService) throws Exception {
        //given
        House house = makeHouse();
        Long houseId = houseService.saveHouse(new HouseDto(house));

        House findHouse = houseRepository.findHouseById(houseId);

        HouseRentContractDto houseRentContractDto = makeHouseRentContractDto(findHouse);
        Long id = houseRentContractService.saveHouseRentContract(houseRentContractDto);

        //when
        List<HouseRentContractDto> houseRentContractByHouse = houseRentContractService.findHouseRentContractByHouse(houseRentContractDto.getHouseDto());

        //then
        assertEquals(1, houseRentContractByHouse.size());
    }
}