package com.org.houserent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class RoadAddressItemProcessor implements ItemProcessor<RoadAddress, RoadAddress> {

    @Override
    public RoadAddress process(RoadAddress roadAddress) throws Exception {
        return roadAddress;
    }
}
