package com.example.schedulerdemo.config;

import com.example.schedulerdemo.service.CustomerBrandDetailsService;
import com.example.schedulerdemo.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    NotificationService sendNotification;

    @Autowired
    CustomerBrandDetailsService customerBrandDetailsService;

    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        /*

        sendNotification.sendNotification("27746422852", 1L);
        sendNotification.sendNotification("15526085550",2L);
        sendNotification.sendNotification("82025091474",3L);
        sendNotification.sendNotification("36023215348",4L);
        sendNotification.sendNotification("26764692960",5L);*/
    }

}
