package com.scrapy.repository.schedule;

import com.scrapy.entity.schedule.TaskScheduleEntity;

import java.time.LocalTime;
import java.util.List;

// Custom Interface
public interface TaskScheduleRepositoryCustom {
    TaskScheduleEntity findByTaskCode(String taskCode);
    List<TaskScheduleEntity> findByReserveTime(LocalTime standardTime);

}
