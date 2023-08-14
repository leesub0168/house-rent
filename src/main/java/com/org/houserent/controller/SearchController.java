package com.org.houserent.controller;

import com.org.houserent.controller.dto.request.SearchRequestDto;
import com.org.houserent.controller.dto.response.ResponseDto;
import com.org.houserent.controller.dto.response.SearchResponseDto;
import com.org.houserent.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping
    public ResponseDto searchHouse(@RequestBody SearchRequestDto searchRequestDto) {
        SearchResponseDto searchResponseDto = searchService.searchAddress(searchRequestDto.getMemberId(),
                searchRequestDto.getSearchAddress(), searchRequestDto.isRoadAddressYn());
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .data(searchResponseDto)
                .build();
    }
}
