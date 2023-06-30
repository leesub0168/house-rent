package com.org.houserent.repository;

import com.org.houserent.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryOld {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByUserId(String userId) {
        try {
            return em.createQuery(
                            "select m from Member m" +
                                    " where m.userId = :userId" +
                                    "   and m.withdrawDate is null", Member.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public Member findByUserIdAndPassword(String userId, String password) {
        try {
            return em.createQuery(
                            "select m from Member m" +
                                    " where m.userId = :userId" +
                                    "   and m.password = :password" +
                                    "   and m.withdrawDate is null ", Member.class)
                    .setParameter("userId", userId)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public List<Member> findByNameAndEmail(String name, String email) {
        return em.createQuery(
                        "select m from Member m" +
                                " where m.name = :name" +
                                "   and m.email = :email" +
                                "   and m.withdrawDate is null ", Member.class)
                .setParameter("name", name)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m " +
                        "where m.withdrawDate is null ", Member.class)
                .getResultList();
    }

}
