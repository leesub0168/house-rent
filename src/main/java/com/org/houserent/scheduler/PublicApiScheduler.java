package com.org.houserent.scheduler;

import com.org.houserent.batch.JusoDbBatchConfiguration;
import com.org.houserent.batch.PublicApiConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicApiScheduler {

    private final JobLauncher jobLauncher;
    private final JusoDbBatchConfiguration jusoDbBatchConfiguration;
    private final PublicApiConfiguration publicApiConfiguration;

    @Scheduled(fixedDelay = 60 * 1000)
    public void callHouseRentApi() {
        log.info("################# scheduler start #####################");
        JobParameters jobParameters = new JobParameters();
        try {
            jobLauncher.run(jusoDbBatchConfiguration.jusoDbTransformJob(), jobParameters);
            jobLauncher.run(publicApiConfiguration.callHouseRentApiJob(), jobParameters);
            jobLauncher.run(publicApiConfiguration.callHouseSaleApiJob(), jobParameters);
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException | JobRestartException e) {
            log.error(e.getMessage());
        }
        log.info("################# scheduler end #####################");
    }
}
