package com.org.houserent.service.dto;

import com.org.houserent.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJoinDto {
    private String user_id;
    private String name;
    private String password;
    private String email;
    private LocalDateTime joinDate;

    @Builder
    public MemberJoinDto(String user_id, String name, String password, String email, LocalDateTime joinDate) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.joinDate = joinDate;
    }

    public Member toEntity() {
        return Member.builder()
                .userId(getUser_id())
                .password(getPassword())
                .name(getName())
                .email(getEmail())
                .joinDate(getJoinDate())
                .build();
    }

    public void setEncryptedPassword(String encryptedPassword) {
        password = encryptedPassword;
    }
}
