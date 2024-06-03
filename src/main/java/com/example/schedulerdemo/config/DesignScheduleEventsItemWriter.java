package com.example.schedulerdemo.config;

import com.example.schedulerdemo.dto.DesignScheduleEvents;
import com.example.schedulerdemo.service.DesignScheduleEventsService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DesignScheduleEventsItemWriter implements ItemWriter<DesignScheduleEvents> {

    private final DesignScheduleEventsService designScheduleEventsService;

    public DesignScheduleEventsItemWriter(DesignScheduleEventsService designScheduleEventsService) {
        this.designScheduleEventsService = designScheduleEventsService;
    }

    public void write(List<? extends DesignScheduleEvents> items) {
        designScheduleEventsService.saveAll((List<DesignScheduleEvents>) items);
    }

    @Override
    public void write(Chunk<? extends DesignScheduleEvents> chunk) throws Exception {
        designScheduleEventsService.saveAll((List<DesignScheduleEvents>) chunk);
    }
}
