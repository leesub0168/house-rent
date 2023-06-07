package com.org.houserent.service;

import com.org.houserent.domain.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private String user_id;
    private String name;
    private String password;
    private String email;

    public MemberDto(String user_id, String name, String password, String email) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public MemberDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.user_id = member.getUser_id();
        this.password = member.getPassword();
    }
}
