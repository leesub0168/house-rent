package com.org.houserent.service;

import com.org.houserent.domain.HouseSaleContract;
import com.org.houserent.repository.HouseSaleContractRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseSaleContractService {
    private final HouseSaleContractRepository houseSaleContractRepository;

    @Transactional
    public Long saveHouseSaleContract(HouseSaleContractDto houseSaleContractDto) {
        HouseSaleContract houseSaleContract = houseSaleContractDto.toEntity();

        houseSaleContractRepository.saveHouseSaleContract(houseSaleContract);

        return houseSaleContract.getId();
    }

    public HouseSaleContractDto findHouseSaleContractById(Long id) {
        HouseSaleContract findHouseSaleContract = houseSaleContractRepository.findHouseSaleContractById(id);

        return new HouseSaleContractDto(findHouseSaleContract);
    }

    public List<HouseSaleContractDto> findHouseSaleContractByHouse(HouseDto houseDto) {
        List<HouseSaleContract> houseSaleContractList = houseSaleContractRepository.findHouseSaleContractByHouse(houseDto.getHouse_id());
        List<HouseSaleContractDto> houseSaleContractDtoList = new ArrayList<>();
        for (HouseSaleContract houseSaleContract : houseSaleContractList) {
            houseSaleContractDtoList.add(new HouseSaleContractDto(houseSaleContract));
        }

        return houseSaleContractDtoList;
    }
}
