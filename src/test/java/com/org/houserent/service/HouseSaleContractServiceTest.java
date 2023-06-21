package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HouseSaleContractServiceTest {

    @Mock
    HouseRepository houseRepository;

    @Autowired
    HouseSaleContractService houseSaleContractService;

    public House makeHouse() {
        return House.builder()
                .bjdong_cd("10800")
                .building_main_num("35")
                .building_sub_num("0")
                .city("서울특별시")
                .detail_address("현대홈타운")
                .dong("공항동")
                .gu("강서구")
                .land_main_num("658")
                .land_sub_num("5")
                .road_name("방화대로7가길")
                .sgg_cd("11500")
                .zipcode("07625")
                .build();
    }

    public HouseSaleContractDto makeHouseSaleContractDto(House house) {
        return HouseSaleContractDto.builder()
                .sale_contract_date(LocalDateTime.now())
                .floor(3.0)
                .sale_price(22500)
                .building_area(35.47)
                .site_area(21.24)
                .right_gbn("")
                .declare_type("직거래")
                .declare_estate_agent_address("")
                .houseDto(new HouseDto(house))
                .build();
    }

    @Test
    @Transactional
    public void 실거래가_계약_정보_저장() throws Exception {
        //given
        House house = makeHouse();

        when(houseRepository.findHouseByRoadAddress(any())).thenReturn(house);

        House findHouse = houseRepository.findHouseByRoadAddress("방화대로7가길 35");

        HouseSaleContractDto houseSaleContractDto = makeHouseSaleContractDto(findHouse);

        //when
        Long id = houseSaleContractService.saveHouseSaleContract(houseSaleContractDto);

        //then
        assertNotNull(id);
    }

    @Test
    @Transactional
    public void 실거래가_계약_정보_아이디로_조회() throws Exception {
        //given
        House house = makeHouse();
        when(houseRepository.findHouseByRoadAddress(any())).thenReturn(house);

        House findHouse = houseRepository.findHouseByRoadAddress("방화대로7가길 35");
        HouseSaleContractDto houseSaleContractDto = makeHouseSaleContractDto(findHouse);
        Long id = houseSaleContractService.saveHouseSaleContract(houseSaleContractDto);

        //when
        HouseSaleContractDto houseSaleContractById = houseSaleContractService.findHouseSaleContractById(id);

        //then
        assertNotNull(houseSaleContractById);
    }
    
    @Test
    @Transactional
    public void 실거래가_계약_정보_집정보로_조회(@Autowired HouseService houseService,
                                   @Autowired HouseRepository houseRepository1) throws Exception {
        //given
        House house = makeHouse();
        Long houseId = houseService.saveHouse(new HouseDto(house));

        House findHouse = houseRepository1.findHouseById(houseId);
        HouseSaleContractDto houseSaleContractDto = makeHouseSaleContractDto(findHouse);
        houseSaleContractService.saveHouseSaleContract(houseSaleContractDto);
        
        //when
        List<HouseSaleContractDto> houseSaleContractByHouse = houseSaleContractService.findHouseSaleContractByHouse(houseSaleContractDto);

        //then
        assertNotNull(houseSaleContractByHouse);
        assertEquals(houseSaleContractByHouse.size(),1);
    }
}