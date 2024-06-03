package com.example.schedulerdemo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "design_schedule_events")
public class DesignScheduleEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id", nullable = false)
    private Long brandID;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "processing_date_time")
    private LocalDateTime processingDateTime;

    @Column(name = "rendering_date_time")
    private LocalDateTime renderingDateTime;

    @Column(name = "sent_date_time")
    private LocalDateTime sentDateTime;

    @Column(name = "delivered_date_time")
    private LocalDateTime deliveredDateTime;

    @Column(name = "status")
    private String status; // Assuming status is a string with values "Successful" or "Failed"

    public Long getId() {
        return id;
    }

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandID) {
        this.brandID = brandID;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getProcessingDateTime() {
        return processingDateTime;
    }

    public void setProcessingDateTime(LocalDateTime processingDateTime) {
        this.processingDateTime = processingDateTime;
    }

    public LocalDateTime getRenderingDateTime() {
        return renderingDateTime;
    }

    public void setRenderingDateTime(LocalDateTime renderingDateTime) {
        this.renderingDateTime = renderingDateTime;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public LocalDateTime getDeliveredDateTime() {
        return deliveredDateTime;
    }

    public void setDeliveredDateTime(LocalDateTime deliveredDateTime) {
        this.deliveredDateTime = deliveredDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

