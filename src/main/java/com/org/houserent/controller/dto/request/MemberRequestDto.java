package com.org.houserent.controller.dto.request;

import com.org.houserent.service.dto.MemberDto;
import com.org.houserent.service.dto.MemberJoinDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberRequestDto {
    private String user_id;
    private String password;
    private String name;
    private String email;

    public MemberDto toMemberDto() {
        return MemberDto.builder()
                .user_id(getUser_id())
                .name(getName())
                .password(getPassword())
                .email(getEmail())
                .build();
    }

    public MemberJoinDto toMemberJoinDto() {
        return MemberJoinDto.builder()
                .user_id(getUser_id())
                .name(getName())
                .password(getPassword())
                .email(getEmail())
                .joinDate(LocalDateTime.now())
                .build();
    }
}
