package com.org.houserent.service;

import com.org.houserent.controller.dto.request.SearchRequestDto;
import com.org.houserent.controller.dto.response.SearchHouseResponseDto;
import com.org.houserent.controller.dto.response.SearchResponseDto;
import com.org.houserent.exception.NonExistHouseException;
import com.org.houserent.service.dto.HouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {
    private final MemberService memberService;
    private final HouseService houseService;

    public SearchResponseDto searchAddress(SearchRequestDto searchRequestDto) {
        Optional<HouseDto> houseDto;
        if(searchRequestDto.isRoadAddressYn()) {
            houseDto = houseService.findHouseByRoadAddress(searchRequestDto.getSearchAddress());
        } else {
            houseDto = houseService.findHouseByLandAddress(searchRequestDto.getSearchAddress());
        }

        houseDto.orElseThrow(() -> new NonExistHouseException("주소 정보를 찾을 수 없습니다."));

        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setHouse(new SearchHouseResponseDto(houseDto.get()));

        return searchResponseDto;
    }
}
