package com.org.houserent.util;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.domain.HouseSaleContract;
import com.org.houserent.exception.NonExistDataException;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.service.dto.HouseRentContractDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PublicApiClientTest {

    @Autowired
    PublicApiClient publicApiClient;

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
                .sgg_nm("강서구")
                .zipcode("07625")
                .build();
    }
    public House makeHouse2() {
        return House.builder()
                .bjdong_cd("11100")
                .building_main_num("11")
                .building_sub_num("0")
                .city("서울특별시")
                .detail_address("")
                .dong("방이동")
                .gu("송파구")
                .land_main_num(48)
                .land_sub_num(2)
                .road_name("위례성대로2길")
                .sgg_cd("11710")
                .sgg_nm("송파구")
                .zipcode("05545")
                .build();
    }

    @Test
    public void 부동산_매매_실거래가_조회_실패() throws Exception {
        //given
        House house = makeHouse();
        houseRepository.save(house);

        //then
        assertEquals(0, publicApiClient.getHouseSaleContractInfo(house).size());
    }

    @Test
    public void 부동산_매매_실거래가_조회_성공() throws Exception {
        //given
        House house = makeHouse();
        houseRepository.save(house);

        //when
        List<HouseSaleContract> houseSaleContractInfo = publicApiClient.getHouseSaleContractInfo(house);

        //then
        for (HouseSaleContract houseSaleContract : houseSaleContractInfo) {
            System.out.println(houseSaleContract);
        }
    }
    
    @Test
    public void 부동산_전월세_실거래가_조회() throws Exception {
        //given
        House house = makeHouse2();
        houseRepository.save(house);

        //when
        List<HouseRentContract> houseRentContractInfo = publicApiClient.getHouseRentContractInfo(house);

        //then
        for (HouseRentContract houseRentContract : houseRentContractInfo) {
            System.out.println(houseRentContract);
        }

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