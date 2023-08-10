package com.org.houserent.controller.dto.request;

import lombok.Getter;

@Getter
public class BookMarkRequestDto {
    private Long houseId;
    private Long memberId;
    private Long bookMarkId;
}
