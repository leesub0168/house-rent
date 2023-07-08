package com.org.houserent.service;

import com.org.houserent.exception.NonExistException;
import com.org.houserent.repository.HouseRentContractRepository;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseRentContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseRentContractService {
    private final HouseRentContractRepository houseRentContractRepository;

    public Long saveHouseRentContract(HouseRentContractDto houseRentContractDto) {
        return houseRentContractRepository.save(houseRentContractDto.toEntity()).getId();
    }

    public HouseRentContractDto findHouseRentContractById(Long id) {
        return houseRentContractRepository.findById(id).map(HouseRentContractDto::new)
                .orElseThrow(() -> new NonExistException("전월세 계약 정보가 존재하지 않습니다."));
    }

    public List<HouseRentContractDto> findHouseRentContractByHouse(HouseDto houseDto) {
        return houseRentContractRepository.findHouseRentContractsByHouse(houseDto.toEntity())
                .stream()
                .map(HouseRentContractDto::new)
                .collect(Collectors.toList());
    }
}
