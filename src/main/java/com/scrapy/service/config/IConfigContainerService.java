package com.scrapy.service.config;

import com.scrapy.model.config.Configuration;

import java.time.LocalDateTime;

public interface IConfigContainerService {

    Configuration get(String code);

    void save(String code, Configuration configuration);

    void saveLatestConfigDate(LocalDateTime value);

    Object getLatestConfigDate();
}
