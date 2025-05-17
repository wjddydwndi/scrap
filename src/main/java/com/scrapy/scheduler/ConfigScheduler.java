package com.scrapy.scheduler;

import com.scrapy.service.config.ConfigServiceImpl;
import com.scrapy.service.config.IConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ConfigScheduler {

    private final IConfigService configService;

    // 1분 설정
    @Scheduled(fixedDelay = 60000, initialDelay = 2000)
    public void configTask() {
        configService.update();
    }
}
