package com.scrapy.service.redis.config;

import com.scrapy.service.redis.RedisService;

import java.time.LocalDateTime;

public interface IConfigRedisService extends RedisService {

    void saveLatestConfigDate(LocalDateTime value);

    Object getLatestConfigDate();

}
