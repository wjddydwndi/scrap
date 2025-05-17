package com.scrapy.entity.mail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMailScheduleEntity is a Querydsl query type for MailScheduleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMailScheduleEntity extends EntityPathBase<MailScheduleEntity> {

    private static final long serialVersionUID = -1620212522L;

    public static final QMailScheduleEntity mailScheduleEntity = new QMailScheduleEntity("mailScheduleEntity");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath fromMail = createString("fromMail");

    public final BooleanPath isSend = createBoolean("isSend");

    public final StringPath message = createString("message");

    public final DateTimePath<java.time.LocalDateTime> reservedTime = createDateTime("reservedTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> sendAttempts = createNumber("sendAttempts", Integer.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public final StringPath toMail = createString("toMail");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QMailScheduleEntity(String variable) {
        super(MailScheduleEntity.class, forVariable(variable));
    }

    public QMailScheduleEntity(Path<? extends MailScheduleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMailScheduleEntity(PathMetadata metadata) {
        super(MailScheduleEntity.class, metadata);
    }

}

