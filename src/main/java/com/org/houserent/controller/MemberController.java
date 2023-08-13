package com.org.houserent.controller;

import com.org.houserent.controller.dto.request.MemberRequestDto;
import com.org.houserent.controller.dto.response.MemberLoginDto;
import com.org.houserent.controller.dto.response.ResponseDto;
import com.org.houserent.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    public static final String MEMBER_ID = "MEMBER_ID";

    @PostMapping("/join")
    public ResponseDto join(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.joinMember(memberRequestDto.toMemberJoinDto());
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("가입되었습니다")
                .build();
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody MemberRequestDto memberRequestDto, HttpSession session) {
        MemberLoginDto memberLoginDto = memberService.login(memberRequestDto.getUser_id(), memberRequestDto.getPassword());
        session.setAttribute(MEMBER_ID, memberLoginDto.getId());
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("로그인 되었습니다.")
                .data(memberLoginDto)
                .build();
    }

    @PostMapping("/checkPassword")
    public ResponseDto checkPasswordBeforeChangePassword(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.checkPasswordBeforeChangePassword(memberRequestDto.getUser_id(), memberRequestDto.getPassword());

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("비밀번호가 일치합니다")
                .build();
    }

    @PostMapping("/updateMemberInfo")
    public ResponseDto updateMemberInfo(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.updateMemberInfo(memberRequestDto.toMemberDto());
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("회원 정보가 수정되었습니다.")
                .build();
    }

    @PostMapping("/changePassword")
    public ResponseDto changePassword(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.changePassword(memberRequestDto.getUser_id(), memberRequestDto.getPassword());
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("비밀번호가 변경되었습니다.")
                .build();
    }

    @PostMapping("/withDraw")
    public ResponseDto withDraw(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.withDrawMember(memberRequestDto.getUser_id(), memberRequestDto.getPassword());
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("탈퇴처리 되었습니다.")
                .build();
    }


    @PostMapping("/logout")
    public ResponseDto logOut(@RequestBody MemberRequestDto memberRequestDto, HttpSession session) {
        session.removeAttribute(MEMBER_ID);
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("로그아웃 되었습니다.")
                .build();
    }
}
