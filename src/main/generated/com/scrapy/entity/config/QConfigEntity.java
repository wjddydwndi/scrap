package com.scrapy.entity.config;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConfigEntity is a Querydsl query type for ConfigEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConfigEntity extends EntityPathBase<ConfigEntity> {

    private static final long serialVersionUID = 1297005631L;

    public static final QConfigEntity configEntity = new QConfigEntity("configEntity");

    public final StringPath category = createString("category");

    public final StringPath code = createString("code");

    public final StringPath codeParam = createString("codeParam");

    public final StringPath codeValue = createString("codeValue");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final BooleanPath enable = createBoolean("enable");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QConfigEntity(String variable) {
        super(ConfigEntity.class, forVariable(variable));
    }

    public QConfigEntity(Path<? extends ConfigEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConfigEntity(PathMetadata metadata) {
        super(ConfigEntity.class, metadata);
    }

}

