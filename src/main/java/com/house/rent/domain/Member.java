package com.house.rent.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime join_date;
    private LocalDateTime last_login_date;
    private LocalDateTime withdraw_date;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getJoin_date() {
        return join_date;
    }

    public LocalDateTime getLast_login_date() {
        return last_login_date;
    }

    public LocalDateTime getWithdraw_date() {
        return withdraw_date;
    }
}
