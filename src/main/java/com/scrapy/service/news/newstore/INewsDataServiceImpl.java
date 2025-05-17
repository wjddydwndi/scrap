package com.scrapy.service.news.newstore;

import com.scrapy.common.util.CommonUtil;
import com.scrapy.common.util.DateFormatUtil;
import com.scrapy.entity.news.NewstoreNewsDataEntity;
import com.scrapy.repository.news.NewstoreNewsDataRepository;
import com.scrapy.service.redis.news.INewsRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class INewsDataServiceImpl implements INewsDataService {

    private final NewstoreNewsDataRepository newstoreNewsDataRepository;

    private final INewsRedisService newsRedisService;

    @Value("${spring.data.redis.enabled}")
    private boolean isRedis;

    public String getLatestNewsDateString() {

        // redis 데이터가 없을 경우. 당일 부터 수집.
        if (isRedis) {
            if (newsRedisService.hasLatestNewsDate()) {
                Date latestNewsDate = (Date) newsRedisService.getLatestNewsDate();
                return DateFormatUtil.formatDate(latestNewsDate, "yyyy-MM-dd");
            }

        } else {
            LocalDateTime latestNewsDate = findLatestNewDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return latestNewsDate.format(formatter);
        }

        return DateFormatUtil.formatDate(new Date(), "yyyy-MM-dd");
    }

    public LocalDateTime getLatestNewsDate() {
        // redis 데이터가 없을 경우. 당일 부터 수집.
        if (isRedis) {
            if (newsRedisService.hasLatestNewsDate()) {
                return (LocalDateTime) newsRedisService.getLatestNewsDate();
            }

        } else {
            LocalDateTime latestNewsDate = findLatestNewDate();
            if (!CommonUtil.isEmpty(latestNewsDate)) {
                return latestNewsDate;
            }
        }

        return LocalDateTime.now();
    }

    private LocalDateTime findLatestNewDate() {
        NewstoreNewsDataEntity newstoreNewsDataEntity = newstoreNewsDataRepository.findTopByOrderByCreateTimeDesc();
        return CommonUtil.isEmpty(newstoreNewsDataEntity) ? LocalDateTime.now().withHour(0).withMinute(0).withSecond(0) : newstoreNewsDataEntity.getCreateTime();
    }
}
