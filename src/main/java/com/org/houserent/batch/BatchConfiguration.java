package com.org.houserent.batch;

import com.org.houserent.HouseRentContractItemProcessor;
import com.org.houserent.batch.entity.JusoRoadNameCd;
import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final EntityManagerFactory emf;

    @Bean
    public Job job() {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jusoDbTransformToHouse())
                .build();
    }

    @Bean
    public Step jusoDbTransformToHouse() {
        return new StepBuilder("jusoDbTransformToHouse", jobRepository)
                .<House, House>chunk(1000, transactionManager)
                .reader(jdbcPagingItemReader())
                .writer(houseJpaItemWriter())
                .build();
    }

    @Bean
    public Step callHouseRentApiUseHouseData() {
        return new StepBuilder("callHouseRentApiUseHouseData", jobRepository)
                .<House, HouseRentContract>chunk(1000, transactionManager)
                .reader(houseJpaPagingItemReader())
                .processor(houseRentContractProcessor())
                .writer(houseRentContractJpaItemWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<House> jdbcPagingItemReader() {
        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("b.control_num", Order.ASCENDING);

        return new JdbcPagingItemReaderBuilder<House>()
                .name("jdbcPagingItemReader")
                .dataSource(dataSource)
                .selectClause("select a.road_address_name , a.si_do_name , a.si_gun_gu_name, a.dong_name, " +
                        "b.building_main_num , b.building_sub_num, b.control_num, " +
                        "substr(c.bjd_cd, 1, 5) as sgg_cd, substr(c.bjd_cd, 6, 10) as bjd_cd, c.land_main_num, c.land_sub_num, c.bjd_dong_name, " +
                        "d.zipcode, d.si_gun_gu_building_name ")
                .fromClause("from juso_road_name_cd a " +
                        "inner join juso_address_info b on a.juso_road_name_cd = b.juso_road_name_cd and a.dong_serial_num = b.dong_serial_num " +
                        "inner join juso_land_address_info c on c.control_num = b.control_num and c.represent_yn = '1' " +
                        "inner join juso_additional_info d on d.control_num = c.control_num")
                .rowMapper(new HouseRowMapper())
                .pageSize(1000)
                .sortKeys(sortKeys)
                .build();
    }

    /*@Bean
    public JpaPagingItemReader<JusoRoadNameCd> jpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<JusoRoadNameCd>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(emf)
                .queryString("select m from JusoRoadNameCd m")
                .pageSize(1000)
                .build();
    }*/

    @Bean
    public JpaItemWriter<House> houseJpaItemWriter() {
        return new JpaItemWriterBuilder<House>()
                .entityManagerFactory(emf)
                .build();
    }

    @Bean
    public JpaPagingItemReader<House> houseJpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<House>()
                .name("houseJpaPagingItemReader")
                .entityManagerFactory(emf)
                .queryString("select a from house a")
                .pageSize(1000)
                .build();
    }

    @Bean
    public ItemProcessor<House, HouseRentContract> houseRentContractProcessor() {
        return new HouseRentContractItemProcessor();
    }

    @Bean
    public JpaItemWriter<HouseRentContract> houseRentContractJpaItemWriter() {
        return new JpaItemWriterBuilder<HouseRentContract>()
                .entityManagerFactory(emf)
                .build();
    }
}
