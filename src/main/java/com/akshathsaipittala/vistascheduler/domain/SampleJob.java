package com.akshathsaipittala.vistascheduler.domain;

import com.akshathsaipittala.vistascheduler.service.SampleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/*@Slf4j
@Component
public class SampleJob implements Job {

    @Autowired
    private SampleJobService jobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobService.executeSampleJob();
    }
}*/

public class SampleJob extends QuartzJobBean {

    //private MyService myService;
    @Autowired
    private SampleJobService jobService;

    private String name;

    /*// Inject "MyService" bean
    public void setMyService(MyService myService) { ... }

    // Inject the "name" job data property
    public void setName(String name) { ... }*/

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        jobService.executeSampleJob();
    }

}
