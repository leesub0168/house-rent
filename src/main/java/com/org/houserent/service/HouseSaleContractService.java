package com.org.houserent.service;

import com.org.houserent.exception.NonExistException;
import com.org.houserent.repository.HouseSaleContractRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseSaleContractService {
    private final HouseSaleContractRepository houseSaleContractRepository;

    @Transactional
    public Long saveHouseSaleContract(HouseSaleContractDto houseSaleContractDto) {
        return houseSaleContractRepository.save(houseSaleContractDto.toEntity()).getId();
    }

    public HouseSaleContractDto findHouseSaleContractById(Long id) {
        return houseSaleContractRepository.findById(id)
                .map(HouseSaleContractDto::new)
                .orElseThrow(() -> new NonExistException("매매 계약 정보가 존재하지 않습니다."));
    }

    public List<HouseSaleContractDto> findHouseSaleContractByHouse(HouseDto houseDto) {
        return houseSaleContractRepository.findHouseSaleContractsByHouse(houseDto.toEntity())
                .stream()
                .map(HouseSaleContractDto::new)
                .collect(Collectors.toList());
    }
}
