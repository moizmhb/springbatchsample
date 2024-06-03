package com.example.schedulerdemo.service;

import com.example.schedulerdemo.dto.CustomerBrandDetails;
import com.example.schedulerdemo.dto.DesignScheduleEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    CustomerBrandDetailsService customerBrandDetailsService;

    @Autowired
    DesignScheduleEventsService designScheduleEventsService;

    public void sendNotification(String number) {
        if(ObjectUtils.isEmpty(number)/* || ObjectUtils.isEmpty(id)*/){
            System.out.println("Number or Id is empty or null");
            return;
        }
        System.out.println("Started execution for number "+  number /*+ " , brandId : " + id*/);
        Optional<CustomerBrandDetails> optionalBrandDetails = customerBrandDetailsService.getLatestActiveBrandByPrimaryMobileNumber(number);
        DesignScheduleEvents designScheduleEventObject = new DesignScheduleEvents();
        if (!optionalBrandDetails.isEmpty() && optionalBrandDetails.isPresent()) {
            CustomerBrandDetails brandDetails = optionalBrandDetails.get();
            if (shouldSendNotification(brandDetails)) {
                designScheduleEventObject = createDesignScheduleEventObject(brandDetails.getId(), "Successful");
                logNotificationSent(brandDetails.getId());
            } else {
                designScheduleEventObject = createDesignScheduleEventObject(brandDetails.getId(), "Failed");
                logNotificationNotSent(brandDetails.getId());
            }
            saveDesignScheduleEvent(designScheduleEventObject);

        } /*else {
            System.out.println();
            logNotificationNotSent(id);
            designScheduleEventObject = createDesignScheduleEventObject(id, false);
        }*/


    }

    private boolean shouldSendNotification(CustomerBrandDetails brandDetails) {
        LocalDate designNextDate = brandDetails.getDesignNextDate().toLocalDate();
        LocalDate today = LocalDate.now();
        System.out.println("Date : " + designNextDate.isEqual(today));
        System.out.println("Day : " + "AlternateDays".equalsIgnoreCase(brandDetails.getAutomatedDesignFreq()));
        return designNextDate.isEqual(today) && "AlternateDays".equalsIgnoreCase(brandDetails.getAutomatedDesignFreq());
    }

    private void logNotificationSent(Long brandId) {
        System.out.println("Log notification has been sent to brandId : " + brandId);
    }

    private void logNotificationNotSent(Long brandId) {
        System.out.println("Log notification has not been sent to brandId : " + brandId);
    }

    private void saveDesignScheduleEvent(DesignScheduleEvents designScheduleEvent) {
        designScheduleEventsService.save(designScheduleEvent);
    }


    public DesignScheduleEvents createDesignScheduleEventObject(Long id, String status) {
        DesignScheduleEvents designScheduleEvents = new DesignScheduleEvents();
        designScheduleEvents.setBrandID(id);
        designScheduleEvents.setStatus(status);
        LocalDateTime now = LocalDateTime.now();
        designScheduleEvents.setDeliveredDateTime(now);
        designScheduleEvents.setProcessingDateTime(now);
        designScheduleEvents.setSentDateTime(now);
        designScheduleEvents.setRenderingDateTime(now);
        designScheduleEvents.setStartDateTime(now);
        return designScheduleEvents;
    }
}
