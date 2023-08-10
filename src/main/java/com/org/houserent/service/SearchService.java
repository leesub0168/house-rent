package com.org.houserent.service;

import com.org.houserent.controller.dto.response.SearchHouseRentResponseDto;
import com.org.houserent.controller.dto.response.SearchHouseResponseDto;
import com.org.houserent.controller.dto.response.SearchHouseSaleResponseDto;
import com.org.houserent.controller.dto.response.SearchResponseDto;
import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.domain.HouseSaleContract;
import com.org.houserent.exception.NonExistHouseException;
import com.org.houserent.repository.HouseRentContractRepository;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.repository.HouseSaleContractRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseRentContractDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {
    private final HouseRepository houseRepository;
    private final HouseRentContractRepository houseRentContractRepository;
    private final HouseSaleContractRepository houseSaleContractRepository;

    public SearchResponseDto searchAddress(String userId, String searchAddress, boolean roadAddressYn) {
        Optional<House> house;
        if(roadAddressYn) {
            house = houseRepository.findHouseByRoadAddress(searchAddress);
        } else {
            house = houseRepository.findHouseByLandAddress(searchAddress);
        }

        house.orElseThrow(() -> new NonExistHouseException("주소 정보를 찾을 수 없습니다."));

        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setHouse(new SearchHouseResponseDto(house.get()));

        List<HouseSaleContract> houseSaleContractList = houseSaleContractRepository.findHouseSaleContractsByHouse(house.get());
        List<HouseRentContract> houseRentContractDtoList = houseRentContractRepository.findHouseRentContractsByHouse(house.get());

        searchResponseDto.setHouseRentList(houseRentContractDtoList.stream()
                .map(SearchHouseRentResponseDto::new)
                .collect(Collectors.toList()));

        searchResponseDto.setHouseSaleList(houseSaleContractList.stream()
                .map(SearchHouseSaleResponseDto::new)
                .collect(Collectors.toList()));

        return searchResponseDto;
    }
}
