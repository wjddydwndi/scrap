package com.scrapy.repository.mail;

import com.scrapy.entity.mail.MailScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailScheduleRepository extends JpaRepository<MailScheduleEntity, Long>, MailScheduleRepositoryCustom {
}
