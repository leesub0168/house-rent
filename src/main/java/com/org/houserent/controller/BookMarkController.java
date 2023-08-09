package com.org.houserent.controller;

import com.org.houserent.controller.dto.response.BookMarkResponseDto;
import com.org.houserent.controller.dto.response.ResponseDto;
import com.org.houserent.service.BookMarkService;
import com.org.houserent.service.dto.BookMarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookMarkController {
    private final BookMarkService bookMarkService;

    @GetMapping("/bookMarkList")
    public ResponseDto bookMarkList(@Param("memberId") Long memberId) {
        List<BookMarkDto> bookMarkDtoList = bookMarkService.bookMarkList(memberId);
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .data(new BookMarkResponseDto(bookMarkDtoList))
                .build();
    }

    @PostMapping("/addBookMark")
    public ResponseDto addBookMark() {
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("추가되었습니다")
                .build();
    }

    @PostMapping("/deleteBookMark")
    public ResponseDto deleteBookMark() {
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("삭제되었습니다.")
                .build();
    }
}

