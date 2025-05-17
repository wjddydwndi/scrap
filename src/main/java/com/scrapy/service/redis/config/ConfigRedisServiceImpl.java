package com.scrapy.service.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfigRedisServiceImpl implements IConfigRedisService {

    private final RedisTemplate<String, Object> configRedisTemplate;

    private final String prefix = "config_";

    private final String dataLatestUpdatedConfig = "data_latest_updated_config";

    @Override
    public void save(String key, Object value) {
        configRedisTemplate.opsForValue().set(prefix.concat(key), value);
    }

    @Override
    public Object get(String key) {
        return configRedisTemplate.opsForValue().get(prefix.concat(key));
    }

    @Override
    public void delete(String key) {
        configRedisTemplate.delete(prefix.concat(key));
    }

    @Override
    public boolean hasKey(String key) {
        return configRedisTemplate.hasKey(prefix.concat(key));
    }

    @Override
    public void saveLatestConfigDate(LocalDateTime value) {
        save(dataLatestUpdatedConfig, value);
    }

    @Override
    public Object getLatestConfigDate() {
        return get(dataLatestUpdatedConfig);
    }
}
