package com.example.schedulerdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job sendNotificationJob;


    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("currentTime", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(sendNotificationJob, jobParameters);
            logger.info("Job Status : {}", jobExecution.getStatus());
            logger.info("Job completed successfully");
        } catch (JobExecutionException e) {
            logger.error("Job failed", e);
        }


        /*

        sendNotification.sendNotification("27746422852", 1L);
        sendNotification.sendNotification("15526085550",2L);
        sendNotification.sendNotification("82025091474",3L);
        sendNotification.sendNotification("36023215348",4L);
        sendNotification.sendNotification("26764692960",5L);*/
    }

}
