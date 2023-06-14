package com.org.houserent.repository;

import com.org.houserent.domain.House;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class HouseRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveHouse(House house) {
        em.persist(house);
    }

    public House findHouseById(Long id) {
        return em.find(House.class, id);
    }

    public House findHouseByRoadAddress(String searchAddress) {
        try {
            return em.createQuery(
                            "select h from House h " +
                                    "where concat(h.road_name,' ', h.building_main_num,'-', h.building_sub_num) like concat('%',:searchAddress, '%')", House.class)
                    .setParameter("searchAddress", searchAddress)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public House findHouseByLandAddress(String searchAddress) {
        try {
            return em.createQuery(
                            "select h from House h " +
                                    "where concat(h.dong,' ', h.land_main_num,'-', h.land_sub_num) like concat('%',:searchAddress, '%')", House.class)
                    .setParameter("searchAddress", searchAddress)
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
