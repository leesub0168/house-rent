package com.org.houserent.controller;

import com.org.houserent.service.MemberDto;
import lombok.Getter;

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
}
