package com.org.houserent.batch;

import com.org.houserent.batch.entity.RoadAddress;
import com.org.houserent.batch.entity.juso_address_info;
import com.org.houserent.util.AddressTranslation;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final DataSource dataSource;
    private final JobCompletionNotificationListener listener;
    private final EntityManagerFactory emf;
    private final AddressTranslation addressTranslation;

    @Bean
    public Job job() {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<RoadAddress, RoadAddress>chunk(100, transactionManager)
                .reader(jdbcPagingItemReader())
                .processor(roadProcessor())
                .writer(roadAddressWriter())
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<RoadAddress, RoadAddress>chunk(100, transactionManager)
                .reader(jsonReader())
                .processor(roadProcessor())
                .writer(roadAddressWriter())
                .build();
    }

    @Bean
    public JsonItemReader<RoadAddress> jsonReader() {
        return new JsonItemReaderBuilder<RoadAddress>()
                .name("roadAddressItemReader")
                .resource(new ClassPathResource("seoul_road_address.json"))
                .jsonObjectReader(new JacksonJsonObjectReader<>(RoadAddress.class))
                .build();
    }

    @Bean
    public JdbcPagingItemReader<RoadAddress> jdbcPagingItemReader() {
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("control_num", Order.ASCENDING);

        return new JdbcPagingItemReaderBuilder<RoadAddress>()
                .name("jdbcPagingItemReader")
                .dataSource(dataSource)
                .selectClause("select distinct a.base_area_num, b.si_gun_gu_name, substr(c.bjd_cd, 1, 5) as sigungu_cd, " +
                        "substr(c.bjd_cd, 6, 10) as bjdong_cd, b.road_address_name as rd_nm, b.dong_name, a.building_main_num, a.building_sub_num, d.building_register_name ")
                .fromClause("from juso_address_info a " +
                        "inner join juso_road_name_cd b on a.juso_road_name_cd = b.juso_road_name_cd and a.dong_serial_num = b.dong_serial_num " +
                        "inner join juso_land_address_info c on a.control_num = c.control_num " +
                        "inner join juso_additional_info d on a.control_num = d.control_num")
                .rowMapper(new RoadAddressRowMapper())
                .pageSize(1000)
                .sortKeys(sortKeys)
                .build();
    }

    @Bean
    public JpaPagingItemReader<juso_address_info> jpaPagingItemReader() {
        JpaPagingItemReader<juso_address_info> roadAddressJpaPagingItemReader = new JpaPagingItemReader<>();
        roadAddressJpaPagingItemReader.setEntityManagerFactory(emf);
        roadAddressJpaPagingItemReader.setQueryString("select a" +
                "from juso_address_info a " +
                "join fetch a.control_num");
        roadAddressJpaPagingItemReader.setPageSize(1000);
        return roadAddressJpaPagingItemReader;
    }

    @Bean
    public RoadAddressItemProcessor roadProcessor() {
        return new RoadAddressItemProcessor();
    }

    @Bean
    public JpaItemWriter<RoadAddress> roadAddressWriter() {
        return new JpaItemWriterBuilder<RoadAddress>()
                .entityManagerFactory(emf)
                .build();
    }

    @Bean
    public JpaItemWriter<juso_address_info> jpaWriter() {
        JpaItemWriter<juso_address_info> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(emf);
        return jpaItemWriter;
    }

}
