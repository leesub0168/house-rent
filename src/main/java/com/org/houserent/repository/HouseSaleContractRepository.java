package com.org.houserent.repository;

import com.org.houserent.domain.HouseSaleContract;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseSaleContractRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveHouseSaleContract(HouseSaleContract houseSaleContract) {
        em.persist(houseSaleContract);
    }

    public HouseSaleContract findHouseSaleContractById(Long id) {
        return em.find(HouseSaleContract.class, id);
    }

    public List<HouseSaleContract> findHouseSaleContractByHouse(Long houseId) {
        return em.createQuery(
                        "select hs from HouseSaleContract hs join hs.house h " +
                                "where h.id = :houseId", HouseSaleContract.class)
                .setParameter("houseId", houseId)
                .getResultList();
    }
}
