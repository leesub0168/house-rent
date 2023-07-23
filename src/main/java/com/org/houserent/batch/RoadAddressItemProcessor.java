package com.org.houserent.batch;

import com.org.houserent.batch.entity.RoadAddress;
import com.org.houserent.util.AddressTranslation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class RoadAddressItemProcessor implements ItemProcessor<RoadAddress, RoadAddress> {

    private final AddressTranslation addressTranslation;

    @Override
    public RoadAddress process(RoadAddress roadAddress) throws Exception {
        log.info("################ " + roadAddress);
        return roadAddress;
    }
}
