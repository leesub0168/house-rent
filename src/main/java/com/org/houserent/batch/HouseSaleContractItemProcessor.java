package com.org.houserent.batch;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseSaleContract;
import com.org.houserent.util.PublicApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HouseSaleContractItemProcessor implements ItemProcessor<House, List<HouseSaleContract>> {

    private final PublicApiClient publicApiClient;
    private final String year;

    @Override
    public List<HouseSaleContract> process(House house) throws Exception {
        return publicApiClient.getHouseSaleContractInfo(house, year);
    }
}
