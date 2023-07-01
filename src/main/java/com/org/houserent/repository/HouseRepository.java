package com.org.houserent.repository;

import com.org.houserent.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    @Query("select h from House h where concat(h.road_name,' ', h.building_main_num,'-', h.building_sub_num) like concat('%',:searchAddress, '%')")
    Optional<House> findHouseByRoadAddress(@Param("searchAddress") String searchAddress);

    @Query("select h from House h where concat(h.dong,' ', h.land_main_num,'-', h.land_sub_num) like concat('%',:searchAddress, '%')")
    Optional<House> findHouseByLandAddress(@Param("searchAddress") String searchAddress);

}
