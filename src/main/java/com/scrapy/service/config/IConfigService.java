package com.scrapy.service.config;

import com.scrapy.model.config.Configuration;

public interface IConfigService {

    void update();

    Configuration get(String category, String code);
}
