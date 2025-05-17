package com.scrapy.entity.schedule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTaskScheduleEntity is a Querydsl query type for TaskScheduleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTaskScheduleEntity extends EntityPathBase<TaskScheduleEntity> {

    private static final long serialVersionUID = 2009904196L;

    public static final QTaskScheduleEntity taskScheduleEntity = new QTaskScheduleEntity("taskScheduleEntity");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath execute = createString("execute");

    public final DateTimePath<java.time.LocalDateTime> expireTime = createDateTime("expireTime", java.time.LocalDateTime.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isComplete = createBoolean("isComplete");

    public final TimePath<java.time.LocalTime> reserveTime = createTime("reserveTime", java.time.LocalTime.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final StringPath taskCode = createString("taskCode");

    public QTaskScheduleEntity(String variable) {
        super(TaskScheduleEntity.class, forVariable(variable));
    }

    public QTaskScheduleEntity(Path<? extends TaskScheduleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskScheduleEntity(PathMetadata metadata) {
        super(TaskScheduleEntity.class, metadata);
    }

}

