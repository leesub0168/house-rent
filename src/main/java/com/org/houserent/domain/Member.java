package com.org.houserent.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime joinDate;
    private LocalDateTime withdrawDate;

    public Member() {}

    public Member(String userId, String password, String name, String email, LocalDateTime joinDate) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.joinDate = joinDate;
    }

    public void withDrawMember() {
        this.withdrawDate = LocalDateTime.now();
    }

    public void updateMemberInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
