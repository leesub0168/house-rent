package com.org.houserent.util;

import com.org.houserent.domain.House;
import com.org.houserent.service.HouseService;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PublicApiClientTest {

    @Autowired
    PublicApiClient publicApiClient;

    @Autowired
    HouseService houseService;

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
                .sgg_nm("강서구")
                .zipcode("07625")
                .build();
    }

    @Test
    @Transactional
    public void 부동산_매매_실거래가_조회() throws Exception {
        //given
        String searchAddress = "방화대로7가길 35";
        boolean isRoadAddress = true;
        House house = makeHouse();
        houseService.saveHouse(new HouseDto(house));

        //when
        List<HouseSaleContractDto> houseSaleContractDtoList = publicApiClient.getHouseSaleContractInfo(searchAddress, isRoadAddress);

        //then
        for (HouseSaleContractDto houseSaleContractDto : houseSaleContractDtoList) {
            System.out.println(houseSaleContractDto);
        }
    }
    
    @Test
    public void 부동산_전월세_실거래가_조회() throws Exception {
        //given
        String searchAddress = "방이동 100-23";
        boolean isRoadAddress = false;
        //when
        publicApiClient.getHouseRentContractInfo(searchAddress, isRoadAddress);
        //then
    }

    @Test
    public void URI빌더_공백_테스트() throws Exception {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://openapi.seoul.go.kr:8088")
                .pathSegment("5a75446d6b6c656539375244465959", "json", "tbLnOpendataRtmsV",
                        "1", "10", "2022","11500", "강서구","10800", "{id}", "{test}", "0658","0005")
                .encode(StandardCharsets.UTF_8)
                .build(" "," ");

        System.out.println(uri);
    }

}