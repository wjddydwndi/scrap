package com.scrapy.service.redis.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRedisServiceImpl implements IUserRedisService {

    private final RedisTemplate<String, Object> sessionRedisTemplate;

    private final String prefix = "user_";

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
}