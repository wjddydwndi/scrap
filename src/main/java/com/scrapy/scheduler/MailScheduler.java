package com.scrapy.scheduler;


import com.scrapy.service.schedule.mail.IMailScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MailScheduler {

    private final IMailScheduleService mailScheduleService;

    @Scheduled(fixedDelay = 5000, initialDelay = 2000)
    public void mailTask() {
        // 스크래핑

        // 매 30분 간격으로 체크.
        // 1. reserve_time 작업목록 체크.
        // 2. 작업목록 요청 진행.
        // 3. 등록.
        mailScheduleService.execute();
    }
}
