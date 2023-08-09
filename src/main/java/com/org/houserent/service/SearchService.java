package com.org.houserent.service;

import com.org.houserent.controller.dto.response.SearchHouseRentResponseDto;
import com.org.houserent.controller.dto.response.SearchHouseResponseDto;
import com.org.houserent.controller.dto.response.SearchHouseSaleResponseDto;
import com.org.houserent.controller.dto.response.SearchResponseDto;
import com.org.houserent.exception.NonExistHouseException;
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
    private final HouseService houseService;
    private final HouseRentContractService houseRentContractService;
    private final HouseSaleContractService houseSaleContractService;

    public SearchResponseDto searchAddress(String userId, String searchAddress, boolean roadAddressYn) {
        Optional<HouseDto> houseDto;
        if(roadAddressYn) {
            houseDto = houseService.findHouseByRoadAddress(searchAddress);
        } else {
            houseDto = houseService.findHouseByLandAddress(searchAddress);
        }

        houseDto.orElseThrow(() -> new NonExistHouseException("주소 정보를 찾을 수 없습니다."));

        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setHouse(new SearchHouseResponseDto(houseDto.get()));

        List<HouseSaleContractDto> houseSaleContractDtoList = houseSaleContractService.findHouseSaleContractByHouse(houseDto.get());
        List<HouseRentContractDto> houseRentContractDtoList = houseRentContractService.findHouseRentContractByHouse(houseDto.get());

        searchResponseDto.setHouseRentList(houseRentContractDtoList.stream()
                .map(SearchHouseRentResponseDto::new)
                .collect(Collectors.toList()));

        searchResponseDto.setHouseSaleList(houseSaleContractDtoList.stream()
                .map(SearchHouseSaleResponseDto::new)
                .collect(Collectors.toList()));

        return searchResponseDto;
    }
}
