package com.scrapy.service.mail;

import com.scrapy.model.mail.MailMessage;
import com.scrapy.model.mail.MailSchedule;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Map;

public interface IMailService {

    boolean send(MailMessage mailMessage) throws MessagingException;
}
