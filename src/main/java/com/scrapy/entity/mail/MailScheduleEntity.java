package com.scrapy.entity.mail;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_mail_schedule")
@Getter
@DynamicUpdate
public class MailScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(name = "to_mail")
    private String toMail;

    @Column(name = "from_mail")
    private String fromMail;

    private String title;

    private String message;

    @Column(name = "reserved_time")
    private LocalDateTime reservedTime;

    @Column(name = "is_send")
    private boolean isSend;

    @Column(name = "send_attempts")
    private Integer sendAttempts;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    public void updateSendAttemptsCount(int count) {
        this.sendAttempts = count;
    }

    public void updateIsSend(boolean isSend) {
        this.isSend = isSend;
    }
}
