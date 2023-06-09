package com.org.houserent.service;

import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.exception.NonExistMemberException;
import com.org.houserent.exception.WrongPasswordException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    public MemberDto createMemberDTO() {
        String userId = "leesub0168";
        String name = "이수빈";
        String password = "asgsdg";
        String email = "sdgsdg@naver.com";

        return new MemberDto(userId, name, password, email);
    }

    @Test
    @Transactional
    public void 회원가입_성공() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();

        //when
        Long memberId = memberService.joinMember(memberDTO);
        MemberDto findMember = memberService.findMemberById(memberId);

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
        assertEquals(loginMember.getName(), memberDTO.getName());
        assertEquals(loginMember.getEmail(), memberDTO.getEmail());
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
    
    @Test
    @Transactional
    public void 회원_탈퇴() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);
        
        //when
        memberService.withDrawMember(memberDTO.getUser_id(), memberDTO.getPassword());
        
        //then
        assertThrows(NonExistMemberException.class, () -> memberService.login(memberDTO.getUser_id(), memberDTO.getPassword()));
    }

    @Test
    @Transactional
    public void id로_회원조회() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);

        //when
        Long id = Long.valueOf("1234");

        //then
        assertThrows(NonExistMemberException.class, () -> memberService.findMemberById(id));
    }

    @Test
    @Transactional
    public void 비밀번호_변경전_패스워드_검증() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);

        //when

        //then
        assertDoesNotThrow(
                () -> memberService.checkPassword(memberDTO.getUser_id(), memberDTO.getPassword())
        );
    }

    @Test
    @Transactional
    public void 회원정보_수정() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);

        String newName = "testName";
        String email = "newMail@naver.com";
        MemberDto newMemberDto = new MemberDto(memberDTO.getUser_id(), newName, memberDTO.getPassword(), email);

        //when
        memberService.updateMemberInfo(newMemberDto);
        MemberDto findMember = memberService.findMemberByUserId(newMemberDto.getUser_id());

        //then
        assertAll(
                () -> assertEquals(findMember.getEmail(), newMemberDto.getEmail()),
                () -> assertEquals(findMember.getName(), newMemberDto.getName()),
                () -> assertEquals(findMember.getUser_id(), newMemberDto.getUser_id())
        );
    }
    
    @Test
    @Transactional
    public void 비밀번호_변경() throws Exception {
        //given
        MemberDto memberDTO = createMemberDTO();
        memberService.joinMember(memberDTO);
        
        //when
        String newPassword = "newpassword";
        memberService.updatePassword(memberDTO.getUser_id(), newPassword);

        //then
        assertDoesNotThrow(() -> memberService.login(memberDTO.getUser_id(), newPassword));
    }
}
