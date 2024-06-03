package com.example.schedulerdemo.config;

import com.example.schedulerdemo.dto.CustomerBrandDetails;
import com.example.schedulerdemo.dto.DesignScheduleEvents;
import com.example.schedulerdemo.service.DesignScheduleEventsService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    DesignScheduleEventsService designScheduleEventsService;

    @Bean
    public Job sendNotificationJob() {
        return new JobBuilder("sendNotificationJob", jobRepository)
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<CustomerBrandDetails, DesignScheduleEvents>chunk(100, transactionManager)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<CustomerBrandDetails> itemReader() {
        JdbcPagingItemReader<CustomerBrandDetails> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setPageSize(100);
        reader.setRowMapper(new BeanPropertyRowMapper<>(CustomerBrandDetails.class));

        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT id, primary_mobile_number, automated_design_freq, design_next_date, created_at, status");
        queryProvider.setFromClause("FROM customer_brand_details");
        queryProvider.setWhereClause("WHERE status = TRUE");
        queryProvider.setSortKey("created_at");

        try {
            reader.setQueryProvider(queryProvider.getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Bean
    public ItemProcessor<CustomerBrandDetails, DesignScheduleEvents> itemProcessor() {

        return brandDetails -> {

            if (shouldSendNotification(brandDetails)) {
                System.out.println("log notification has been sent to brandId");
                return createDesignScheduleEventObject(brandDetails.getId(), true);
            } else {
                System.out.println("log notification has not been sent to brandId");
                return createDesignScheduleEventObject(brandDetails.getId(), false);
            }
        };
    }

    @Bean
    public ItemWriter<DesignScheduleEvents> itemWriter() {
        System.out.println("items : ");
        return items -> items.forEach(designScheduleEventsService::save);
    }

    private boolean shouldSendNotification(CustomerBrandDetails brandDetails) {
        LocalDate designNextDate = brandDetails.getDesignNextDate().toLocalDate();
        LocalDate today = LocalDate.now();
        System.out.println("Today :  " + designNextDate.isEqual(today));
        System.out.println("AlternateDays : "  + "AlternateDays".equalsIgnoreCase(brandDetails.getAutomatedDesignFreq()));
        System.out.println(brandDetails.getAutomatedDesignFreq());
        return (designNextDate.isEqual(today) && "AlternateDays".equalsIgnoreCase(brandDetails.getAutomatedDesignFreq())) || "Daily".equalsIgnoreCase(brandDetails.getAutomatedDesignFreq());
    }

    private DesignScheduleEvents createDesignScheduleEventObject(Long id, boolean status) {
        DesignScheduleEvents designScheduleEvents = new DesignScheduleEvents();
        designScheduleEvents.setBrandID(id);
        designScheduleEvents.setStatus(status ? "Successful" : "Failed");
        LocalDateTime now = LocalDateTime.now();
        designScheduleEvents.setDeliveredDateTime(now);
        designScheduleEvents.setProcessingDateTime(now);
        designScheduleEvents.setSentDateTime(now);
        designScheduleEvents.setRenderingDateTime(now);
        designScheduleEvents.setStartDateTime(now);
        return designScheduleEvents;
    }
}
