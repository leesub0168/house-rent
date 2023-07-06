package com.org.houserent.service;

import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.exception.NonExistMemberException;
import com.org.houserent.exception.WrongPasswordException;
import com.org.houserent.service.dto.MemberDto;
import com.org.houserent.service.dto.MemberJoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    public MemberJoinDto createMemberJoinDto() {
        String userId = "leesub0168";
        String name = "이수빈";
        String password = "asgsdg";
        String email = "sdgsdg@naver.com";

        return MemberJoinDto.builder()
                .user_id(userId)
                .name(name)
                .password(password)
                .email(email)
                .joinDate(LocalDateTime.now())
                .build();
    }

    @Test
    public void 회원가입_성공() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();

        //when
        Long memberId = memberService.joinMember(memberJoinDto);
        MemberDto findMember = memberService.findMemberById(memberId);

        //then
        assertEquals(memberJoinDto.getUser_id(), findMember.getUser_id());
    }
    
    @Test
    public void 회원가입_실패_중복아이디() throws Exception {
        //given
        MemberJoinDto memberJoinDto1 = createMemberJoinDto();
        MemberJoinDto memberJoinDto2 = createMemberJoinDto();

        //when
        memberService.joinMember(memberJoinDto1);
        
        //then
        assertThrows(DuplicateMemberException.class, () -> memberService.joinMember(memberJoinDto2));
    }
    
    @Test
    public void 로그인_성공() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);

        String userId = "leesub0168";
        String password = "asgsdg";

        //when
        MemberDto loginMember = memberService.login(userId, password);

        //then
        assertEquals(loginMember.getName(), memberJoinDto.getName());
        assertEquals(loginMember.getEmail(), memberJoinDto.getEmail());
    }
    
    @Test
    public void 로그인_실패_비밀번호_불일치() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);

        String userId = "leesub0168";
        String password = "wrongpwd";

        //when
        assertThrows(WrongPasswordException.class, () -> memberService.login(userId, password));
    }
    
    @Test
    public void 회원_탈퇴() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);
        
        //when
        String userId = "leesub0168";
        String password = "asgsdg";
        memberService.withDrawMember(userId, password);
        
        //then
        assertThrows(NonExistMemberException.class, () -> memberService.login(userId, password));
    }

    @Test
    public void id로_회원조회() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);

        //when
        Long id = 1234L;

        //then
        assertThrows(NonExistMemberException.class, () -> memberService.findMemberById(id));
    }

    @Test
    public void 비밀번호_변경전_패스워드_검증() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);

        //when
        String userId = "leesub0168";
        String password = "asgsdg";

        //then
        assertDoesNotThrow(
                () -> memberService.checkPasswordBeforeChangePassword(userId, password)
        );
    }

    @Test
    public void 회원정보_수정() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);

        String newName = "testName";
        String email = "newMail@naver.com";
        MemberDto newMemberDto = MemberDto.builder()
                .user_id(memberJoinDto.getUser_id())
                .name(newName)
                .password(memberJoinDto.getPassword())
                .email(email)
                .build();

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
    public void 비밀번호_변경() throws Exception {
        //given
        MemberJoinDto memberJoinDto = createMemberJoinDto();
        memberService.joinMember(memberJoinDto);
        
        //when
        String newPassword = "newpassword";
        memberService.changePassword(memberJoinDto.getUser_id(), newPassword);

        //then
        assertDoesNotThrow(() -> memberService.login(memberJoinDto.getUser_id(), newPassword));
    }
}
