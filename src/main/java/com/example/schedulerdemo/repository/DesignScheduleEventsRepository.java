package com.example.schedulerdemo.repository;

import com.example.schedulerdemo.dto.DesignScheduleEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignScheduleEventsRepository extends JpaRepository<DesignScheduleEvents, Long> {
    // Define any custom queries if needed
}
