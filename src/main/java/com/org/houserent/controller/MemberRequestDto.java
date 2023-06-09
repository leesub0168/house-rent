package com.org.houserent.controller;

import com.org.houserent.service.MemberDto;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private String user_id;
    private String password;
    private String name;
    private String email;

    public MemberRequestDto() {
    }

    public MemberRequestDto(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public MemberRequestDto(String user_id, String password, String name, String email) {
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public MemberDto toMemberDto() {
        return new MemberDto(getUser_id(), getName(), getPassword(), getEmail());
    }
}
