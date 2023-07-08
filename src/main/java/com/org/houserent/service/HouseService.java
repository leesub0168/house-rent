package com.org.houserent.service;

import com.org.houserent.domain.House;
import com.org.houserent.exception.NonExistHouseException;
import com.org.houserent.repository.HouseRepository;
import com.org.houserent.service.dto.HouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    @Transactional
    public Long saveHouse(HouseDto houseDto) {
        return houseRepository.save(houseDto.toEntity()).getId();
    }

    public HouseDto findHouseById(Long id) {
        return houseRepository.findById(id).map(HouseDto::new)
                .orElseThrow(() -> new NonExistHouseException("주소 정보가 존재하지 않습니다."));
    }

    public Optional<HouseDto> findHouseByRoadAddress(String searchAddress) {
        return houseRepository.findHouseByRoadAddress(searchAddress)
                .map(HouseDto::new);
    }

    public Optional<HouseDto> findHouseByLandAddress(String searchAddress) {
        return houseRepository.findHouseByLandAddress(searchAddress)
                .map(HouseDto::new);
    }

}
