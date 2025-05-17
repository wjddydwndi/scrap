package com.scrapy.service.config;

import com.scrapy.model.config.Configuration;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapyConfigContainerService implements IConfigContainerService{
    private Map<String, Configuration> scrapyConfigMap = new HashMap<>();

    private LocalDateTime latestConfigDate = LocalDateTime.MIN;

    public Configuration get(String code) {
        return scrapyConfigMap.get(code.toLowerCase());
    }

    public void save(String code, Configuration configuration) {
        scrapyConfigMap.put(code.toLowerCase(), configuration);
    }

    public void saveLatestConfigDate(LocalDateTime value) {
        this.latestConfigDate = value;
    }

    public Object getLatestConfigDate() {
        return latestConfigDate;
    }
}
