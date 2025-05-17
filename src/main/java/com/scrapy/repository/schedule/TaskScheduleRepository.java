package com.scrapy.repository.schedule;

import com.scrapy.entity.schedule.TaskScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskScheduleRepository extends JpaRepository<TaskScheduleEntity, Long>, TaskScheduleRepositoryCustom {
}
