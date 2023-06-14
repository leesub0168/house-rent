package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.service.dto.HouseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HouseServiceTest {

    @Autowired
    HouseService houseService;

    @Test
    public void 도로명_주소_검색() throws Exception {
        //given
        String searchAddress = "올림픽로32길 42-23";
        HouseDto findHouse = houseService.findHouseByRoadAddress(searchAddress);
        //when

        assertEquals("올림픽로32길", findHouse.getRoad_name());
        //then
    }
    
    @Test
    public void 지번_주소_검색() throws Exception {
        //given
        String searchAddress = "방이동 100-23";
        HouseDto findHouse = houseService.findHouseByLandAddress(searchAddress);

        //when
        assertEquals("올림픽로32길", findHouse.getRoad_name());
        //then
    }
}