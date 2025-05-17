package com.scrapy.entity.schedule;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "t_task_schedule")
@Getter
public class TaskScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "reserve_time")
    private LocalTime reserveTime;

    @Column(name = "execute")
    private String execute;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_complete")
    private boolean isComplete;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
