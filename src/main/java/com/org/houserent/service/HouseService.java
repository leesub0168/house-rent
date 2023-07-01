package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.exception.NonExistHouseException;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.service.dto.HouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    @Transactional
    public Long saveHouse(HouseDto houseDto) {
        House house = House.builder()
                .sgg_cd(houseDto.getSgg_cd())
                .sgg_nm(houseDto.getSgg_nm())
                .bjdong_cd(houseDto.getBjdong_cd())
                .zipcode(houseDto.getZipcode())
                .city(houseDto.getCity())
                .gu(houseDto.getGu())
                .dong(houseDto.getDong())
                .road_name(houseDto.getRoad_name())
                .building_main_num(houseDto.getBuilding_main_num())
                .building_sub_num(houseDto.getBuilding_sub_num())
                .land_main_num(houseDto.getLand_main_num())
                .land_sub_num(houseDto.getLand_sub_num())
                .detail_address(houseDto.getDetail_address())
                .build();

        houseRepository.save(house);

        return house.getId();
    }

    public HouseDto findHouseById(Long id) {
        Optional<House> findHouse = houseRepository.findById(id);
        return new HouseDto(findHouse.orElseThrow(() -> new NonExistHouseException("주소 정보가 존재하지 않습니다.")));
    }

    public Optional<HouseDto> findHouseByRoadAddress(String searchAddress) {
        Optional<House> findHouse = houseRepository.findHouseByRoadAddress(searchAddress);

        return findHouse.map(h -> new HouseDto(h));
    }

    public Optional<HouseDto> findHouseByLandAddress(String searchAddress) {
        Optional<House> findHouse = houseRepository.findHouseByLandAddress(searchAddress);

        return findHouse.map(h -> new HouseDto(h));
    }

}
