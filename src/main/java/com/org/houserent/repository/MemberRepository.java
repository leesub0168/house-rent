package com.org.houserent.repository;

import com.org.houserent.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByNameAndEmail(String name, String email) {
        return em.createQuery(
                "select m from Member m" +
                        " where m.name = :name" +
                        "   and m.email = :email", Member.class)
                .setParameter("name", name)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }

}
