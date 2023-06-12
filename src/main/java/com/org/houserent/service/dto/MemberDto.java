package com.org.houserent.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.org.houserent.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberDto {
    private String user_id;
    private String name;
    private String password;
    private String email;

    @Builder
    public MemberDto(String user_id, String name, String password, String email, String test) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Builder
    public MemberDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MemberDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.user_id = member.getUserId();
        this.password = member.getPassword();
    }

}
