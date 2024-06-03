package com.example.schedulerdemo.repository;

import com.example.schedulerdemo.dto.CustomerBrandDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerBrandDetailsRepository extends JpaRepository<CustomerBrandDetails, Long> {

    @Query("SELECT c FROM CustomerBrandDetails c WHERE c.primaryMobileNumber = :primaryMobileNumber AND c.status = true ORDER BY c.createdAt DESC")
    Page<CustomerBrandDetails> findLatestActiveBrandByPrimaryMobileNumber(@Param("primaryMobileNumber") String primaryMobileNumber, Pageable pageable);

}
