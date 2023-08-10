package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.domain.Member;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.repository.MemberRepository;
import com.org.houserent.service.dto.BookMarkDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BookMarkServiceTest {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    BookMarkService bookMarkService;

    @Autowired
    MemberRepository memberRepository;

    public Member getMember() {
        return Member.builder()
                .userId("leesub0168")
                .name("이수빈")
                .password("asdfsdf")
                .email("asgd@naver.com")
                .build();
    }

    public House getHouse() {
        return House.builder()
                .sgg_cd("11500")
                .sgg_nm("강서구")
                .city("서울특별시")
                .dong("공항동")
                .gu("강서구")
                .zipcode("07625")
                .road_name("방화대로7가길")
                .building_main_num("35")
                .building_sub_num("0")
                .bjdong_cd("10800")
                .land_main_num(658)
                .land_sub_num(5)
                .detail_address("현대홈타운")
                .build();
    }

    @Test
    public void 북마크_리스트_조회() throws Exception {
        //give
        Member member = getMember();
        memberRepository.save(member);

        House house = getHouse();
        houseRepository.save(house);


        bookMarkService.addBookMark(house.getId(), member.getId());

        List<BookMarkDto> bookMarkDtos = bookMarkService.bookMarkList(member.getId());

        assertEquals(1, bookMarkDtos.size());

    }


    @Test
    public void 북마크_등록() throws Exception {
        //given
        Member member = getMember();
        memberRepository.save(member);

        House house = getHouse();
        houseRepository.save(house);

        //when
        bookMarkService.addBookMark(house.getId(), member.getId());

        //then
    }

    @Test
    public void 북마크_삭제() throws Exception {
        //given
        Member member = getMember();
        memberRepository.save(member);

        House house = getHouse();
        houseRepository.save(house);

        //when
        Long bookMarkId = bookMarkService.addBookMark(house.getId(), member.getId());

        //when
        bookMarkService.deleteBookMark(bookMarkId);

        List<BookMarkDto> bookMarkDtos = bookMarkService.bookMarkList(member.getId());

        //then
        assertEquals(0, bookMarkDtos.size());
    }
}