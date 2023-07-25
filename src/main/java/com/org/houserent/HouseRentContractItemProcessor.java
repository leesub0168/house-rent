package com.org.houserent;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class HouseRentContractItemProcessor implements ItemProcessor<House, HouseRentContract> {
    @Override
    public HouseRentContract process(House item) throws Exception {
        return null;
    }
}
