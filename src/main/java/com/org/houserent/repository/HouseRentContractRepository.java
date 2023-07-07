package com.org.houserent.repository;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRentContractRepository extends JpaRepository<HouseRentContract, Long> {
    List<HouseRentContract> findHouseRentContractsByHouse(House house);
}
