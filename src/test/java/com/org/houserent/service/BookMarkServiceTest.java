package com.org.houserent.service;

import com.org.houserent.domain.BookMark;
import com.org.houserent.domain.House;
import com.org.houserent.domain.Member;
import com.org.houserent.repository.BookMarkRepository;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookMarkServiceTest {

    @Autowired
    BookMarkRepository bookMarkRepository;

    @Autowired
    HouseRepository houseRepository;

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


        BookMark bookMark = BookMark.builder()
                .member(member)
                .house(house)
                .build();

        BookMark save = bookMarkRepository.save(bookMark);

        assertNotNull(save);
    }
}