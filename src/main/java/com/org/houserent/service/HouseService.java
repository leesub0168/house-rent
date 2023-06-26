package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.exception.NonExistMemberException;
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

        houseRepository.saveHouse(house);

        return house.getId();
    }

    public HouseDto findHouseById(Long id) {
        House findHouse = houseRepository.findHouseById(id);
        if(findHouse == null) throw new NonExistMemberException("주소 정보가 존재하지 않습니다.");

        return new HouseDto(findHouse);
    }

    public Optional<HouseDto> findHouseByRoadAddress(String searchAddress) {
        House findHouse = houseRepository.findHouseByRoadAddress(searchAddress);
//        if(findHouse == null) throw new NonExistMemberException("주소 정보가 존재하지 않습니다.");
        return Optional.ofNullable(new HouseDto(findHouse));
    }

    public Optional<HouseDto> findHouseByLandAddress(String searchAddress) {
        House findHouse = houseRepository.findHouseByLandAddress(searchAddress);
//        if(findHouse == null) throw new NonExistMemberException("주소 정보가 존재하지 않습니다.");

        return Optional.ofNullable(new HouseDto(findHouse));
    }

}
