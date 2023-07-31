package com.org.houserent.batch;

import com.org.houserent.domain.House;
import com.org.houserent.repository.HouseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBatchTest
@SpringBootTest(classes = {
        JusoDbBatchConfiguration.class,
        House.class,
        HouseRepository.class,
        TestBatchConfig.class})
class JusoDbBatchConfigurationTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    JpaItemWriter<House> writer;

    @Autowired
    EntityManagerFactory emf;

    @Test
    public void reader_테스트() throws Exception {
        House house = House.builder()
                .sgg_cd("11110")
                .sgg_nm("종로구")
                .bjdong_cd("10100")
                .city("서울특별시")
                .gu("종로구")
                .detail_address("리더테스트네임")
                .zipcode("03046")
                .build();

        houseRepository.save(house);

        JpaPagingItemReader<House> reader = new JpaPagingItemReaderBuilder<House>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(emf)
                .queryString("select h from House h")
                .pageSize(100)
                .build();

        reader.open(new ExecutionContext());

        assertEquals("리더테스트네임", reader.read().getDetail_address());

    }

    @Test
    @Transactional
    public void writer_테스트() throws Exception {
        //given
        House house = House.builder()
                .sgg_cd("11110")
                .sgg_nm("종로구")
                .bjdong_cd("10100")
                .city("서울특별시")
                .gu("종로구")
                .detail_address("인텔빌라")
                .zipcode("03046")
                .build();

        //when
        Chunk<House> houseChunk = new Chunk<House>();
        houseChunk.add(house);

        writer.setEntityManagerFactory(emf);
        writer.write(houseChunk);

        List<House> all = houseRepository.findAll();
        //then

        assertEquals(1, all.size());
    }

}