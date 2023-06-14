package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class BookMark {
    @Id @GeneratedValue
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    public BookMark() {
    }

    @Builder
    public BookMark(Member member, House house) {
        this.member = member;
        this.house = house;
    }
}
