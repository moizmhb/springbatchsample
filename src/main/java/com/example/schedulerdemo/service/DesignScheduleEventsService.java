package com.example.schedulerdemo.service;

import com.example.schedulerdemo.dto.DesignScheduleEvents;
import com.example.schedulerdemo.repository.DesignScheduleEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public DesignScheduleEvents save(DesignScheduleEvents event) {
        System.out.println("Save : " + event.getBrandID());
        return designScheduleEventsRepository.save(event);
    }

    @Transactional
    public void saveAll(List<DesignScheduleEvents> designScheduleEventsList) {
        designScheduleEventsRepository.saveAll(designScheduleEventsList);
    }

    public void deleteById(Long id) {
        designScheduleEventsRepository.deleteById(id);
    }

}

