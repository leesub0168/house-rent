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
        String rd_nm = roadAddress.getRd_nm();
        List<HouseDto> addressInfoList = addressTranslation.getAddressInfoList(rd_nm);
        System.out.println("################## " + roadAddress.getRd_nm() + " , count =  " + addressInfoList.size());

        return roadAddress;
    }
}
