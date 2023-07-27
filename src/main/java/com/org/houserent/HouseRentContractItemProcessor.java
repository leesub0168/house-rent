package com.org.houserent;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.util.PublicApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HouseRentContractItemProcessor implements ItemProcessor<House, List<HouseRentContract>> {

    private final PublicApiClient publicApiClient;

    @Override
    public List<HouseRentContract> process(House house) throws Exception {
        return publicApiClient.getHouseRentContractInfo(house);
    }
}
