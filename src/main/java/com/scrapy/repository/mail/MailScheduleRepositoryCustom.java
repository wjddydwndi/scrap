package com.scrapy.repository.mail;

import com.scrapy.entity.mail.MailScheduleEntity;
import com.scrapy.model.mail.MailSchedule;

import java.util.List;

// Custom Interface
public interface MailScheduleRepositoryCustom {
    List<MailScheduleEntity> findBySendAttempts(int sendAttempts);

    long updateSendAttempts(MailSchedule mailSchedule);

    long updateIsSend(MailSchedule mailSchedule);
}
