package com.org.houserent.repository;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.domain.HouseSaleContract;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveHouse(House house) {
        em.persist(house);
    }

    public void saveHouseSaleContract(HouseSaleContract houseSaleContract) {
        em.persist(houseSaleContract);
    }

    public void saveHouseRentContract(HouseRentContract houseRentContract) {
        em.persist(houseRentContract);
    }

    public House findHouseById(Long id) {
        return em.find(House.class, id);
    }

    public House findHouseByRoadAddress(String keyword) {
        try {
            return em.createQuery(
                            "select h from House h " +
                                    "where concat(h.road_name,' ', h.building_main_num,'-', h.building_sub_num) like concat('%',:keyword, '%')", House.class)
                    .setParameter("keyword", keyword)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    public House findHouseByLandAddress(String keyword) {
        try {
            return em.createQuery(
                            "select h from House h " +
                                    "where concat(h.dong,' ', h.land_main_num,'-', h.land_sub_num) like concat('%',:keyword, '%')", House.class)
                    .setParameter("keyword", keyword)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public HouseSaleContract findHouseSaleContractById(Long id) {
        return em.find(HouseSaleContract.class, id);
    }

    public List<HouseSaleContract> findHouseSaleContractByHouse(House house) {
        return em.createQuery(
                        "select h from HouseSaleContract h " +
                                "where h.house = :house", HouseSaleContract.class)
                .setParameter("house", house)
                .getResultList();
    }

    public HouseRentContract findHouseRentContractById(Long id) {
        return em.find(HouseRentContract.class, id);
    }

    public List<HouseRentContract> findHouseRentContractByHouse(House house) {
        return em.createQuery(
                        "select h from HouseRentContract h " +
                                "where h.house = :house", HouseRentContract.class)
                .setParameter("house", house)
                .getResultList();
    }



}
