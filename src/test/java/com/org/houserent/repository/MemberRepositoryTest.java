package com.org.houserent.repository;

import com.org.houserent.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() throws Exception {
        //given
        String userid = "leesub0168";
        String name = "이수빈";
        String password = "asgsdg";
        String email = "sdgsdg@naver.com";
        Member member = new Member(userid, password, name, email);

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertTrue(member == findMember);
    }
}