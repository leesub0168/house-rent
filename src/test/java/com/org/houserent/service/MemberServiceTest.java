package com.org.houserent.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    public MemberDto createMemberDTO() {
        String userid = "leesub0168";
        String name = "이수빈";
        String password = "asgsdg";
        String email = "sdgsdg@naver.com";

        return new MemberDto(userid, name, password, email);
    }

    @Test
    @Transactional
    public void 회원가입_성공() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();

        //when
        Long memberId = memberService.joinMember(memberDTO);
        MemberDto findMember = memberService.findMember(memberId);

        //then
        assertEquals(memberDTO.getUser_id(), findMember.getUser_id());
    }
    
    @Test
    public void 회원가입_실패_중복아이디() throws Exception {
        //given


        //when
        
        //then
    }
    
}