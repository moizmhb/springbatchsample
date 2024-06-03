package com.example.schedulerdemo.service;

import com.example.schedulerdemo.dto.DesignScheduleEvents;
import com.example.schedulerdemo.repository.DesignScheduleEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignScheduleEventsService {

    @Autowired
    private DesignScheduleEventsRepository designScheduleEventsRepository;

    public List<DesignScheduleEvents> findAll() {
        return designScheduleEventsRepository.findAll();
    }

    public Optional<DesignScheduleEvents> findById(Long id) {
        return designScheduleEventsRepository.findById(id);
    }

    public DesignScheduleEvents save(DesignScheduleEvents event) {
        return designScheduleEventsRepository.save(event);
    }

    public void deleteById(Long id) {
        designScheduleEventsRepository.deleteById(id);
    }

}

