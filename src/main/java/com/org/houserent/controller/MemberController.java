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
        Long id = memberService.joinMember(memberRequestDto.toMemberDto());
        return ResultDto.builder()
                .status(HttpStatus.OK)
                .message("가입되었습니다")
                .data(id)
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
}
