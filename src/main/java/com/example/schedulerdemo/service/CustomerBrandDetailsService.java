package com.example.schedulerdemo.service;

import com.example.schedulerdemo.dto.CustomerBrandDetails;
import com.example.schedulerdemo.repository.CustomerBrandDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        Page<CustomerBrandDetails> page = customerBrandDetailsRepository.findLatestActiveBrandByPrimaryMobileNumber(primaryMobileNumber, pageable);
        return page.getContent().isEmpty() ? Optional.empty() : Optional.ofNullable(page.getContent().get(0));
    }

}
