package com.scrapy.entity.news;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNewstoreRequestCodeEntity is a Querydsl query type for NewstoreRequestCodeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewstoreRequestCodeEntity extends EntityPathBase<NewstoreRequestCodeEntity> {

    private static final long serialVersionUID = 1652550517L;

    public static final QNewstoreRequestCodeEntity newstoreRequestCodeEntity = new QNewstoreRequestCodeEntity("newstoreRequestCodeEntity");

    public final StringPath category = createString("category");

    public final StringPath codeId = createString("codeId");

    public final StringPath codeName = createString("codeName");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final StringPath type = createString("type");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QNewstoreRequestCodeEntity(String variable) {
        super(NewstoreRequestCodeEntity.class, forVariable(variable));
    }

    public QNewstoreRequestCodeEntity(Path<? extends NewstoreRequestCodeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewstoreRequestCodeEntity(PathMetadata metadata) {
        super(NewstoreRequestCodeEntity.class, metadata);
    }

}

