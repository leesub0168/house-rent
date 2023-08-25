package com.org.houserent.service;

import com.org.houserent.service.dto.HouseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class HouseServiceTest {

    @Autowired
    HouseService houseService;

    public HouseDto makeHouseDto() {
        return HouseDto.builder()
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

    @Test
    public void 집_정보_저장_아이디로_집_조회() throws Exception {
        //given
        HouseDto houseDto = makeHouseDto();
        //when
        Long id = houseService.saveHouse(houseDto);
        HouseDto findHouseDto = houseService.findHouseById(id);

        //then
        assertEquals(findHouseDto.getRoad_name(), houseDto.getRoad_name());
        assertEquals(findHouseDto.getDetail_address(), houseDto.getDetail_address());
    }

    @Test
    public void 도로명_주소_검색() throws Exception {
        //given
        HouseDto houseDto = makeHouseDto();
        Long id = houseService.saveHouse(houseDto);

        //when
        String searchAddress = "방화대로7가길 35";
        Optional<HouseDto> findHouse = houseService.findHouseByRoadAddress(searchAddress);

        assertEquals("방화대로7가길", findHouse.get().getRoad_name());
        //then
    }
    
    @Test
    public void 지번_주소_검색() throws Exception {
        //given
        HouseDto houseDto = makeHouseDto();
        Long id = houseService.saveHouse(houseDto);

        String searchAddress = "공항동 658-5";
        Optional<HouseDto> findHouse = houseService.findHouseByLandAddress(searchAddress);

        //when
        assertEquals("방화대로7가길", findHouse.get().getRoad_name());
        //then
    }
}