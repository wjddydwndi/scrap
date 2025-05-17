package com.scrapy.repository.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scrapy.entity.config.ConfigEntity;
import com.scrapy.entity.config.QConfigEntity;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// 해당 어노테이션을 사용해서 스프링에 해당 클래스가 Repository로 사용되도록 등록한다.
@RequiredArgsConstructor
public class ConfigRepositoryImpl implements ConfigRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QConfigEntity configEntity = QConfigEntity.configEntity;

    public List<ConfigEntity> findAll() {

        return queryFactory.selectFrom(configEntity)
                .where(configEntity.enable.eq(true)).fetch();
    }

    public List<ConfigEntity> findByUpdateTime(LocalDateTime latestUpdatedTime) {

        return queryFactory.selectFrom(configEntity)
                .where(configEntity.enable.eq(true)
                  .and(configEntity.updateTime.after(latestUpdatedTime)))
                .fetch();
    }
}
