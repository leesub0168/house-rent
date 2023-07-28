package com.org.houserent.batch;

import com.org.houserent.domain.House;
import com.org.houserent.domain.HouseRentContract;
import com.org.houserent.domain.HouseSaleContract;
import com.org.houserent.util.PublicApiClient;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PublicApiConfiguration {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory emf;
    private final PublicApiClient publicApiClient;

    @Bean
    public Job callHouseRentApiJob() {
        return new JobBuilder("callHouseRentApiJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(callHouseRentApiUseHouseData())
                .build();
    }

    @Bean
    public Job callHouseSaleApiJob() {
        return new JobBuilder("callHouseSaleApiJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(callHouseSaleApiUseHouseData())
                .build();
    }

    @Bean
    public Step callHouseRentApiUseHouseData() {
        return new StepBuilder("callHouseRentApiUseHouseData", jobRepository)
                .<House, List<HouseRentContract>>chunk(100, transactionManager)
                .reader(houseJpaPagingItemReader())
                .processor(houseRentContractProcessor())
                .writer(houseRentContractJpaItemListWriter())
                .build();
    }

    @Bean
    public Step callHouseSaleApiUseHouseData() {
        return new StepBuilder("callHouseSaleApiUseHouseData", jobRepository)
                .<House, List<HouseSaleContract>>chunk(100, transactionManager)
                .reader(houseJpaPagingItemReader())
                .processor(houseSaleContractProcessor())
                .writer(houseSaleContractJpaItemListWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<House> houseJpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<House>()
                .name("houseJpaPagingItemReader")
                .entityManagerFactory(emf)
                .queryString("select a from House a")
                .pageSize(100)
                .build();
    }

    @Bean
    public ItemProcessor<House, List<HouseRentContract>> houseRentContractProcessor() {
        return new HouseRentContractItemProcessor(publicApiClient);
    }

    @Bean
    public ItemProcessor<House, List<HouseSaleContract>> houseSaleContractProcessor() {
        return new HouseSaleContractItemProcessor(publicApiClient);
    }

    @Bean
    public JpaItemListWriter<HouseRentContract> houseRentContractJpaItemListWriter() {
        JpaItemWriter<HouseRentContract> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(emf);

        JpaItemListWriter<HouseRentContract> jpaItemListWriter = new JpaItemListWriter<>(jpaItemWriter);
        jpaItemListWriter.setEntityManagerFactory(emf);

        return jpaItemListWriter;
    }

    @Bean
    public JpaItemListWriter<HouseSaleContract> houseSaleContractJpaItemListWriter() {
        JpaItemWriter<HouseSaleContract> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(emf);

        JpaItemListWriter<HouseSaleContract> jpaItemListWriter = new JpaItemListWriter<>(jpaItemWriter);
        jpaItemListWriter.setEntityManagerFactory(emf);

        return jpaItemListWriter;
    }
}
