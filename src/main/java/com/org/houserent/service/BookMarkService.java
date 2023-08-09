package com.org.houserent.service;

import com.org.houserent.repository.BookMarkRepository;
import com.org.houserent.service.dto.BookMarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookMarkService {
    private final BookMarkRepository bookMarkRepository;

    public List<BookMarkDto> bookMarkList(Long memberId) {
        return bookMarkRepository.findBookMarkByMemberId(memberId).stream()
                .map(bookMark -> new BookMarkDto(bookMark))
                .collect(Collectors.toList());

    }
}
