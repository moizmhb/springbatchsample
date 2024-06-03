package com.example.schedulerdemo.service;

import com.example.schedulerdemo.dto.CustomerBrandDetails;
import com.example.schedulerdemo.repository.CustomerBrandDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerBrandDetailsService {

    private final CustomerBrandDetailsRepository customerBrandDetailsRepository;

    @Autowired
    public CustomerBrandDetailsService(CustomerBrandDetailsRepository customerBrandDetailsRepository) {
        this.customerBrandDetailsRepository = customerBrandDetailsRepository;
    }

    public Optional<CustomerBrandDetails> getLatestActiveBrandByPrimaryMobileNumber(String primaryMobileNumber) {
        Pageable pageable = PageRequest.of(0, 1);
        //Page<Object[]> latestActiveBrandByPrimaryMobileNumber = customerBrandDetailsRepository.findLatestActiveBrandByPrimaryMobileNumber(primaryMobileNumber, pageable);
        Page<CustomerBrandDetails> page = customerBrandDetailsRepository.findLatestActiveBrandByPrimaryMobileNumber(primaryMobileNumber, pageable);
        return page.getContent().isEmpty() ? Optional.empty() : Optional.ofNullable(page.getContent().get(0));
    }

    public void saveCustomerBrandDetailsService() throws InterruptedException {
        CustomerBrandDetails customerBrandDetails = new CustomerBrandDetails();
        LocalDateTime now = LocalDateTime.now();
        customerBrandDetails.setCreatedAt(now);
        customerBrandDetails.setPrimaryMobileNumber("8989871887");
        customerBrandDetails.setAutomatedDesignFreq("Daily");
        customerBrandDetails.setDesignStartDate(now);
        customerBrandDetails.setDesignNextDate(now);
        customerBrandDetails.setLastSentDate(now);
        customerBrandDetails.setStatus(true);
       /* Thread.sleep(1000);*/
        for (int i = 0; i < 100; i++) {
            if(i % 5 == 0){
                //customerBrandDetails.setPrimaryMobileNumber("9039107752");
                customerBrandDetails.setAutomatedDesignFreq("AlternateDays");
                /*if(100%10 == 0){
                    customerBrandDetails.setStatus(true);
                }*/
            }
            customerBrandDetailsRepository.save(customerBrandDetails);
        }

    }
}
