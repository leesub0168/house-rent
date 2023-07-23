package com.org.houserent.batch;

import com.org.houserent.batch.entity.RoadAddress;
import com.org.houserent.batch.entity.juso_address_info;
import com.org.houserent.util.AddressTranslation;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

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
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return new StepBuilder("step1", jobRepository)
                .<juso_address_info, RoadAddress>chunk(100, transactionManager)
                .reader(jpaPagingItemReader())
                .processor(jusoItemProcessor())
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
    public JpaPagingItemReader<juso_address_info> jpaPagingItemReader() {
        JpaPagingItemReader<juso_address_info> roadAddressJpaPagingItemReader = new JpaPagingItemReader<>();
        roadAddressJpaPagingItemReader.setEntityManagerFactory(emf);
        roadAddressJpaPagingItemReader.setQueryString("select o from juso_address_info o");
        roadAddressJpaPagingItemReader.setPageSize(1000);
        return roadAddressJpaPagingItemReader;
    }

    @Bean
    public RoadAddressItemProcessor roadProcessor() {
        return new RoadAddressItemProcessor(addressTranslation);
    }

    @Bean
    public JusoItemProcessor jusoItemProcessor() {
        return new JusoItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<RoadAddress> roadAddressWriter() {
        return new JdbcBatchItemWriterBuilder<RoadAddress>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO house_rent.road_address " +
                        "(sgg_cd, road_number, bjd_serial_number, road_name, road_english_name, city, gu, bjd_type, bjd_code, bjd_dong, " +
                        "parent_road_number, parent_road_name, new_address_use_yn, change_history_info_reason, change_history_info, bjd_number, road_code) " +
                        "VALUES(:sigungu_cd, :rd_sn, :bjdong_sn, :rd_nm, :eng_rd_nm, :sido_nm, :sigungu_nm, :bjdong_gbn, :bjdong_cd, :bjdong_nm," +
                        " :upper_rd_sn, :upper_rd_nm, :use_yn, :chang_hist_caus_cd, :chang_hist_info, :bjdong_no, :rd_code)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JpaItemWriter<juso_address_info> jpaWriter() {
        JpaItemWriter<juso_address_info> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(emf);
        return jpaItemWriter;
    }

}
