package com.scrapy.service.redis.news;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewsRedisServiceImpl implements INewsRedisService {
    private final RedisTemplate<String, Object> sessionRedisTemplate;

    private final String prefix = "news_";

    private final String dataLatestNewsDate = "data_latest_news_date";

    @Override
    public void save(String key, Object value) {
        sessionRedisTemplate.opsForValue().set(prefix.concat(key), value);
    }

    @Override
    public Object get(String key) {
        return sessionRedisTemplate.opsForValue().get(prefix.concat(key));
    }

    @Override
    public void delete(String key) {
        sessionRedisTemplate.delete(prefix.concat(key));
    }

    @Override
    public boolean hasKey(String key) {
        return sessionRedisTemplate.hasKey(prefix.concat(key));
    }

    @Override
    public void saveLatestNewsDate(LocalDateTime value) {
        save(dataLatestNewsDate, value);
    }

    @Override
    public Object getLatestNewsDate() {
        return get(dataLatestNewsDate);
    }

    @Override
    public boolean hasLatestNewsDate() {
        return hasKey(dataLatestNewsDate);
    }
}
