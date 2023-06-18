package com.org.houserent.repository;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseRentContractRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveHouseRentContract(HouseRentContract houseRentContract) {
        em.persist(houseRentContract);
    }

    public HouseRentContract findHouseRentContractById(Long id) {
        return em.find(HouseRentContract.class, id);
    }

    public List<HouseRentContract> findHouseRentContractByHouse(Long houseId) {
        return em.createQuery(
                        "select hr from HouseRentContract hr join hr.house h " +
                                "where h.id = :houseId", HouseRentContract.class)
                .setParameter("houseId", houseId)
                .getResultList();
    }
}
