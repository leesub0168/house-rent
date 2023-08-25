package com.org.houserent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMark {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_mark_seq")
    @SequenceGenerator(name = "book_mark_seq", sequenceName = "book_mark_seq", allocationSize = 1)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Builder
    public BookMark(Member member, House house) {
        this.member = member;
        this.house = house;
    }
}
