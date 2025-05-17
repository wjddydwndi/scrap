package com.scrapy.entity.config;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_config")
@Getter
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String category;

    private String code;

    @Column(name = "code_value")
    private String codeValue;

    @Column(name = "code_param")
    private String codeParam;

    private boolean enable;

    private String description;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
