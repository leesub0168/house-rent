package com.org.houserent.controller.dto.response;

import com.org.houserent.service.dto.BookMarkDto;
import lombok.Getter;

import java.util.List;

@Getter
public class BookMarkResponseDto {
    private List<BookMarkDto> houseList;

    public BookMarkResponseDto(List<BookMarkDto> houseList) {
        this.houseList = houseList;
    }
}
