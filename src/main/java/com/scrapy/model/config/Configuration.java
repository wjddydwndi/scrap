package com.scrapy.model.config;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Configuration {

    private int seq;
    private String category;
    private String code;
    private String codeValue;
    private String codeParam;
    private boolean enable;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Configuration(int seq, String category, String code, String codeValue, String codeParam, boolean enable, String description, LocalDateTime createTime, LocalDateTime updateTime) {
        this.seq = seq;
        this.category = category;
        this.code = code;
        this.codeValue = codeValue;
        this.codeParam = codeParam;
        this.enable = enable;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
