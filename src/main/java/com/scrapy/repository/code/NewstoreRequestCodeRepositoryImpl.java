package com.scrapy.repository.code;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scrapy.entity.news.NewstoreRequestCodeEntity;
import com.scrapy.entity.news.QNewstoreRequestCodeEntity;
import lombok.RequiredArgsConstructor;

// 해당 어노테이션을 사용해서 스프링에 해당 클래스가 Repository로 사용되도록 등록한다.
@RequiredArgsConstructor
public class NewstoreRequestCodeRepositoryImpl implements NewstoreRequestCodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QNewstoreRequestCodeEntity newstoreRequestCodeEntity = QNewstoreRequestCodeEntity.newstoreRequestCodeEntity;

    public NewstoreRequestCodeEntity findByTypeAndCodeName(String type, String codeName) {

        return queryFactory.selectFrom(newstoreRequestCodeEntity)
                .where(newstoreRequestCodeEntity.type.eq(type).and(newstoreRequestCodeEntity.codeName.eq(codeName))).fetchFirst();
    }
}
