package com.scrapy.entity.news;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNewstoreNewsDataEntity is a Querydsl query type for NewstoreNewsDataEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewstoreNewsDataEntity extends EntityPathBase<NewstoreNewsDataEntity> {

    private static final long serialVersionUID = 1987221450L;

    public static final QNewstoreNewsDataEntity newstoreNewsDataEntity = new QNewstoreNewsDataEntity("newstoreNewsDataEntity");

    public final StringPath byline = createString("byline");

    public final StringPath categoryId = createString("categoryId");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dateline = createDateTime("dateline", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> envelopedAt = createDateTime("envelopedAt", java.time.LocalDateTime.class);

    public final StringPath hilight = createString("hilight");

    public final StringPath images = createString("images");

    public final StringPath newsId = createString("newsId");

    public final StringPath printingPage = createString("printingPage");

    public final StringPath providerId = createString("providerId");

    public final StringPath providerLinkPage = createString("providerLinkPage");

    public final StringPath providerNewsId = createString("providerNewsId");

    public final DateTimePath<java.time.LocalDateTime> publishedAt = createDateTime("publishedAt", java.time.LocalDateTime.class);

    public final StringPath publisherCode = createString("publisherCode");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final StringPath subtitle = createString("subtitle");

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QNewstoreNewsDataEntity(String variable) {
        super(NewstoreNewsDataEntity.class, forVariable(variable));
    }

    public QNewstoreNewsDataEntity(Path<? extends NewstoreNewsDataEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewstoreNewsDataEntity(PathMetadata metadata) {
        super(NewstoreNewsDataEntity.class, metadata);
    }

}

