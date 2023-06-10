package com.org.houserent.controller;

import com.org.houserent.service.MemberDto;
import com.org.houserent.service.MemberService;
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

    @PostMapping("/join")
    public ResultDto join(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.joinMember(memberRequestDto.toMemberDto());
        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("가입되었습니다")
                .build();
    }

    @PostMapping("/login")
    public ResultDto login(@RequestBody MemberRequestDto memberRequestDto) {
        MemberDto memberDto = memberService.login(memberRequestDto.getUser_id(), memberRequestDto.getPassword());
        MemberResponseDto memberResponseDto = new MemberResponseDto(memberDto.getName(), memberDto.getEmail());
        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("로그인 되었습니다.")
                .data(memberResponseDto)
                .build();
    }

    @PostMapping("/checkPassword")
    public ResultDto checkPasswordBeforeChangePassword(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.checkPasswordBeforeChangePassword(memberRequestDto.getUser_id(), memberRequestDto.getPassword());

        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("비밀번호가 일치합니다")
                .build();
    }

    @PostMapping("/updateMemberInfo")
    public ResultDto updateMemberInfo(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.updateMemberInfo(memberRequestDto.toMemberDto());
        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("회원 정보가 수정되었습니다.")
                .build();
    }

    @PostMapping("/changePassword")
    public ResultDto changePassword(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.changePassword(memberRequestDto.getUser_id(), memberRequestDto.getPassword());
        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("비밀번호가 변경되었습니다.")
                .build();
    }

    @PostMapping("/withDraw")
    public ResultDto withDraw(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.withDrawMember(memberRequestDto.getUser_id(), memberRequestDto.getPassword());
        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("탈퇴처리 되었습니다.")
                .build();
    }
}
