package com.house.rent.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {
    @Id
    private Long id;
    private String user_id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime join_date;
    private LocalDateTime last_login_date;
    private LocalDateTime withdraw_date;

    public Member() {}

    public Member(String user_id, String password, String name, String email) {
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
