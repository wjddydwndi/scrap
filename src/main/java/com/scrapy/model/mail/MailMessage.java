package com.scrapy.model.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class MailMessage {
    private String to;
    private String from;
    private String subject;
    private String message;
}
