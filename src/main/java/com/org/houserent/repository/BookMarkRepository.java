package com.org.houserent.repository;

import com.org.houserent.domain.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
    @Query("select b from BookMark b where b.member.id = :memberId")
    List<BookMark> findBookMarkByMemberId(@Param("memberId") Long memberId);
}
