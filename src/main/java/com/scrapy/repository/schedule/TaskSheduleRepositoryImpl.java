package com.scrapy.repository.schedule;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scrapy.entity.schedule.QTaskScheduleEntity;
import com.scrapy.entity.schedule.TaskScheduleEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

// 해당 어노테이션을 사용해서 스프링에 해당 클래스가 Repository로 사용되도록 등록한다.

@RequiredArgsConstructor
public class TaskSheduleRepositoryImpl implements TaskScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final EntityManager entityManager; // bulk 연산 후, 영속성 컨텍스트 초기화

    private final QTaskScheduleEntity taskScheduleEntity = QTaskScheduleEntity.taskScheduleEntity;

    @Override
    public TaskScheduleEntity findByTaskCode(String taskCode) {
        return null;
    }

    @Override
    public List<TaskScheduleEntity> findByReserveTime(LocalTime standardTime) {

        LocalTime endTime = standardTime.plusSeconds(59);

        return queryFactory.selectFrom(taskScheduleEntity)
                .where(taskScheduleEntity.reserveTime.between(standardTime, endTime)
                        .and(taskScheduleEntity.isActive.eq(true))
                        .and(taskScheduleEntity.isComplete.eq(false))
                        .and(taskScheduleEntity.expireTime.before(LocalDateTime.now())))
                .fetch(); // expireTime > 현재시간
    }


}
