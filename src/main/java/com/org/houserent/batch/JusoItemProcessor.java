package com.org.houserent.batch;

import com.org.houserent.batch.entity.RoadAddress;
import com.org.houserent.batch.entity.juso_address_info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class JusoItemProcessor implements ItemProcessor<juso_address_info, RoadAddress> {
    @Override
    public RoadAddress process(juso_address_info item) throws Exception {

        return RoadAddress.builder()
                .rd_nm(item.getControl_num())
                .build();
    }
}
