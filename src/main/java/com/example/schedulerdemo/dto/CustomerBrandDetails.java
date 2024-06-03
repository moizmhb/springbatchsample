package com.example.schedulerdemo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer_brand_details")
public class CustomerBrandDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primary_mobile_number")
    private String primaryMobileNumber;

    @Column(name = "automated_design_freq")
    private String automatedDesignFreq; // assuming this is a string with values like "daily" or "alternate days"

    @Column(name = "design_start_date")
    private LocalDateTime designStartDate;

    @Column(name = "design_next_date")
    private LocalDateTime designNextDate;

    @Column(name = "last_sent_date")
    private LocalDateTime lastSentDate;

    @Column(name = "created_at", updatable = false)
    @Generated(GenerationTime.INSERT)
    private LocalDateTime createdAt;

    @Column(name = "status")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public String getPrimaryMobileNumber() {
        return primaryMobileNumber;
    }

    public void setPrimaryMobileNumber(String primaryMobileNumber) {
        this.primaryMobileNumber = primaryMobileNumber;
    }

    public String getAutomatedDesignFreq() {
        return automatedDesignFreq;
    }

    public void setAutomatedDesignFreq(String automatedDesignFreq) {
        this.automatedDesignFreq = automatedDesignFreq;
    }

    public LocalDateTime getDesignStartDate() {
        return designStartDate;
    }

    public void setDesignStartDate(LocalDateTime designStartDate) {
        this.designStartDate = designStartDate;
    }

    public LocalDateTime getDesignNextDate() {
        return designNextDate;
    }

    public void setDesignNextDate(LocalDateTime designNextDate) {
        this.designNextDate = designNextDate;
    }

    public LocalDateTime getLastSentDate() {
        return lastSentDate;
    }

    public void setLastSentDate(LocalDateTime lastSentDate) {
        this.lastSentDate = lastSentDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
