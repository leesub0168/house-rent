package com.org.houserent.batch;

import com.org.houserent.domain.House;
import org.springframework.batch.item.ItemProcessor;

public class HouseItemProcessor implements ItemProcessor<House, House> {
    @Override
    public House process(House house) throws Exception {
        return house;
    }
}
