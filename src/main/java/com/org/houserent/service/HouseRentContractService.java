package com.org.houserent.service;

import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.repository.HouseRentContractRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseRentContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseRentContractService {
    private final HouseRentContractRepository houseRentContractRepository;

    public Long saveHouseRentContract(HouseRentContractDto houseRentContractDto) {
        HouseRentContract houseRentContract = houseRentContractDto.toEntity();
        houseRentContractRepository.saveHouseRentContract(houseRentContract);
        return houseRentContract.getId();
    }

    public HouseRentContractDto findHouseRentContractById(Long id) {
        HouseRentContract houseRentContract = houseRentContractRepository.findHouseRentContractById(id);
        return new HouseRentContractDto(houseRentContract);
    }

    public List<HouseRentContractDto> findHouseRentContractByHouse(HouseDto houseDto) {
        List<HouseRentContract> houseRentContractByHouse =
                houseRentContractRepository.findHouseRentContractByHouse(houseDto.getHouse_id());
        List<HouseRentContractDto> result = new ArrayList<>();
        for (HouseRentContract houseRentContract : houseRentContractByHouse) {
            result.add(new HouseRentContractDto(houseRentContract));
        }
        return result;
    }
}
