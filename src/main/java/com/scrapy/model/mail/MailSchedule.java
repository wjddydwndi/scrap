package com.scrapy.model.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class MailSchedule {

    private long seq;
    private String toMail;
    private String fromMail;
    private String title;
    private String message;
    private LocalDateTime reservedTime;
    private boolean isSend;
    private Integer sendAttempts;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;

    public MailSchedule(long seq, String toMail, String fromMail, String title, String message, LocalDateTime reservedTime, boolean isSend, int sendAttempts, LocalDateTime updateTime, LocalDateTime createTime) {
        this.seq = seq;
        this.toMail = toMail;
        this.fromMail = fromMail;
        this.title = title;
        this.message = message;
        this.reservedTime = reservedTime;
        this.isSend = isSend;
        this.sendAttempts = sendAttempts;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }
}
