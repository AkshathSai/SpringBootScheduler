package com.akshathsaipittala.vistascheduler.scheduler;

import com.akshathsaipittala.vistascheduler.domain.SampleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
@EnableAutoConfiguration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
@Slf4j
public class Scheduler {


    @Bean
    public JobDetail jobDetail() {
       return JobBuilder.newJob().ofType(SampleJob.class)
               .storeDurably()
               .withIdentity("Quartz Job Detail")
               .withDescription("Invoke Sample Job Service")
               .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {

        int frequencyInSec = 10;
        LOGGER.info("Configuring trigger to fire every {} seconds", frequencyInSec);

        return newTrigger().forJob(job)
                .withIdentity(TriggerKey.triggerKey("Qrtz_Trigger"))
                .withDescription("Sample trigger")
                .withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever())
                .build();
    }

}
