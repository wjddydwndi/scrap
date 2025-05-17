package com.scrapy.service.redis;

public interface RedisService {
    void save(String key, Object value);

    Object get(String key);

    void delete(String key);

    boolean hasKey(String key);
}
