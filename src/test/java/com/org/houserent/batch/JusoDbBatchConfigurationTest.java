package com.org.houserent.batch;

import com.org.houserent.domain.House;
import com.org.houserent.repository.HouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@SpringBootTest
class JusoDbBatchConfigurationTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    JpaPagingItemReader<House> reader;

    @Test
    public void reader_테스트() throws Exception {
        House house = House.builder()
                .sgg_cd("11110")
                .sgg_nm("종로구")
                .bjdong_cd("10100")
                .city("서울특별시")
                .gu("종로구")
                .detail_address("청운벽산빌리지")
                .zipcode("03046")
                .build();

        houseRepository.save(house);

        reader.open(new ExecutionContext());

        assertEquals("청운벽산빌리지", reader.read().getDetail_address());

    }

}