package com.org.houserent.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.org.houserent.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberLoginDto {
    private String name;
    private String email;
    private Long id;

    @Builder
    public MemberLoginDto(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public MemberLoginDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.id = member.getId();
    }
}
