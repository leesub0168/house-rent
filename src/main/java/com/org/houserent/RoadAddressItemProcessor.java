package com.org.houserent;

import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.util.AddressTranslation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RoadAddressItemProcessor implements ItemProcessor<RoadAddress, RoadAddress> {

    private final AddressTranslation addressTranslation;

    @Override
    public RoadAddress process(RoadAddress roadAddress) throws Exception {
        return roadAddress;
    }
}
