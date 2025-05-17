package com.scrapy.model.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class TaskSchedule {
    private int seq;
    private String taskCode;
    private LocalTime reserveTime;
    private String description;
    private String execute;
    private boolean isActive;
    private boolean isComplete;
    private LocalDateTime expireTime;
    private LocalDateTime createTime;
}

