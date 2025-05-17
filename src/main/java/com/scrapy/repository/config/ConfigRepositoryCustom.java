package com.scrapy.repository.config;

import com.scrapy.entity.config.ConfigEntity;

import java.time.LocalDateTime;
import java.util.List;

// Custom Interface
public interface ConfigRepositoryCustom {
    List<ConfigEntity> findAll();

    List<ConfigEntity> findByUpdateTime(LocalDateTime latestUpdatedTime);
}
