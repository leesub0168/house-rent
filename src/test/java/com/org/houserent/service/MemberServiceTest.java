package com.org.houserent.service;

import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.exception.WrongPasswordException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Transactional
    public void 회원가입_실패_중복아이디() throws Exception {
        //given
        MemberDto memberDto1 = createMemberDTO();
        MemberDto memberDto2 = createMemberDTO();

        //when
        memberService.joinMember(memberDto1);
        
        //then
        assertThrows(DuplicateMemberException.class, () -> memberService.joinMember(memberDto2));
    }
    
    @Test
    @Transactional
    public void 로그인_성공() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);

        //when
        MemberDto loginMember = memberService.login(memberDTO.getUser_id(), memberDTO.getPassword());

        //then
        assertEquals(loginMember.getUser_id(), memberDTO.getUser_id());
    }
    
    @Test
    @Transactional
    public void 로그인_실패_비밀번호_불일치() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);

        //when
        
        //then
        assertThrows(WrongPasswordException.class, () -> memberService.login(memberDTO.getUser_id(), "wrongpwd"));
    }
    
}