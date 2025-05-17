package com.scrapy.service.redis.news;

import com.scrapy.service.redis.RedisService;

import java.time.LocalDateTime;

public interface INewsRedisService extends RedisService {
    void saveLatestNewsDate(LocalDateTime value);

    Object getLatestNewsDate();

    boolean hasLatestNewsDate();
}
