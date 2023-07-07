package com.org.houserent.repository;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseSaleContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseSaleContractRepository extends JpaRepository<HouseSaleContract, Long> {
    List<HouseSaleContract> findHouseSaleContractsByHouse(House house);
}
