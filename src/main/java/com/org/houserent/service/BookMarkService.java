package com.org.houserent.service;

import com.org.houserent.domain.BookMark;
import com.org.houserent.domain.House;
import com.org.houserent.domain.Member;
import com.org.houserent.repository.BookMarkRepository;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.repository.MemberRepository;
import com.org.houserent.service.dto.BookMarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookMarkService {
    private final BookMarkRepository bookMarkRepository;
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;

    public List<BookMarkDto> bookMarkList(Long memberId) {
        return bookMarkRepository.findBookMarkByMemberId(memberId).stream()
                .map(bookMark -> new BookMarkDto(bookMark))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addBookMark(Long houseId, Long memberId) {
        Optional<House> house = houseRepository.findById(houseId);
        Optional<Member> member = memberRepository.findById(memberId);

        BookMark bookMark = BookMark.builder()
                .house(house.get())
                .member(member.get())
                .build();

        return bookMarkRepository.save(bookMark).getId();
    }

    @Transactional
    public void deleteBookMark(Long bookMarkId) {
        bookMarkRepository.deleteById(bookMarkId);
    }
}
