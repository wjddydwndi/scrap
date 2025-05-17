package com.scrapy.repository.mail;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scrapy.entity.mail.MailScheduleEntity;
import com.scrapy.entity.mail.QMailScheduleEntity;
import com.scrapy.model.mail.MailSchedule;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// 해당 어노테이션을 사용해서 스프링에 해당 클래스가 Repository로 사용되도록 등록한다.
@RequiredArgsConstructor
public class MailScheduleRepositoryImpl implements MailScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final EntityManager entityManager; // bulk 연산 후, 영속성 컨텍스트 초기화

    private final QMailScheduleEntity mailScheduleEntity = QMailScheduleEntity.mailScheduleEntity;

    public List<MailScheduleEntity> findBySendAttempts(int sendAttempts) {

        return queryFactory.selectFrom(mailScheduleEntity)
                .where(mailScheduleEntity.isSend.eq(false)
                        .and(mailScheduleEntity.reservedTime.before(LocalDateTime.now()))
                        .and(mailScheduleEntity.sendAttempts.lt(sendAttempts))).fetch();
    }

    @Override
    public long updateSendAttempts(MailSchedule mailSchedule) {

        long result = queryFactory.update(mailScheduleEntity)
                .set(mailScheduleEntity.sendAttempts, mailSchedule.getSendAttempts())
                .where(mailScheduleEntity.seq.eq(mailSchedule.getSeq()))
                .execute();

        entityManager.flush();
        entityManager.clear();

        return result;
    }

    @Override
    public long updateIsSend(MailSchedule mailSchedule) {
        long result = queryFactory.update(mailScheduleEntity)
                .set(mailScheduleEntity.isSend, mailSchedule.isSend())
                .where(mailScheduleEntity.seq.eq(mailSchedule.getSeq()))
                .execute();

        entityManager.flush();
        entityManager.clear();

        return result;
    }
}
