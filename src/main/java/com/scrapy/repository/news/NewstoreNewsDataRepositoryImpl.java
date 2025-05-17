package com.scrapy.repository.news;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scrapy.entity.news.NewstoreNewsDataEntity;
import com.scrapy.entity.news.QNewstoreNewsDataEntity;
import lombok.RequiredArgsConstructor;

// 해당 어노테이션을 사용해서 스프링에 해당 클래스가 Repository로 사용되도록 등록한다.
@RequiredArgsConstructor
public class NewstoreNewsDataRepositoryImpl implements NewstoreNewsDataRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QNewstoreNewsDataEntity qNewstoreNewsDataEntity = QNewstoreNewsDataEntity.newstoreNewsDataEntity;

    @Override
    public NewstoreNewsDataEntity findTopByOrderByCreateTimeDesc() {

        NewstoreNewsDataEntity newstoreNewsDataEntity = queryFactory.selectFrom(qNewstoreNewsDataEntity)
                .orderBy(qNewstoreNewsDataEntity.seq.desc())
                .limit(1)
                .fetchFirst();

        return newstoreNewsDataEntity;
    }
}
