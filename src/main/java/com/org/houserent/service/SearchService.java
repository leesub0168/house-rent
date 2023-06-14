package com.org.houserent.service;

import com.org.houserent.controller.dto.response.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {
    private final MemberService memberService;
    private final HouseService houseService;
//
//    public SearchResponseDto searchAddress(String userId, String keyword) {
//
//    }
}
