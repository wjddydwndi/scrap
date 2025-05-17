package com.scrapy.service.mail;

import com.scrapy.common.enums.ConfigEnum;
import com.scrapy.common.log.Log;
import com.scrapy.model.mail.MailMessage;
import com.scrapy.service.redis.config.IConfigRedisService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {

    private final IConfigRedisService configRedisService;

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

    @Value("${spring.data.redis.enabled}")
    private boolean isRedis;

    @Value("${spring.mail.properties.mail.from}")
    private String from;

    @Value("${spring.mail.properties.mail.cc}")
    private String cc;

    @PostConstruct
    public void initialize() {

        if (isRedis) {
            if (configRedisService.hasKey(ConfigEnum.code_config_mail_properties_from.getCode())) {
                this.from = String.valueOf(configRedisService.get(ConfigEnum.code_config_mail_properties_from.getCode()));
            }

            if (configRedisService.hasKey(ConfigEnum.code_config_mail_properties_cc.getCode())) {
                this.cc = String.valueOf(configRedisService.get(ConfigEnum.code_config_mail_properties_cc.getCode()));
            }
        }
    }

    // https://velog.io/@tjddus0302/Spring-Boot-%EB%A9%94%EC%9D%BC-%EB%B0%9C%EC%86%A1-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-Gmail
    @Async("executeSendMail")
    @Override
    public boolean send(MailMessage mailMessage) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(mailMessage.getTo());
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(mailMessage.getSubject());
            mimeMessageHelper.setText(mailMessage.getMessage(), true);
            // 메일 발송.
            javaMailSender.send(mimeMessage);
        } catch(MessagingException e) {
            Log.error("occurred during email sending. sucject={}, to={}, e={}", mailMessage.getSubject(), mailMessage.getTo(), e.getMessage());
            return false;
        }

        Log.info("Email sent successfully. sucject={}, to={}", mailMessage.getSubject(), mailMessage.getTo());
        return true;
    }


    // 어드민에서 예약할 때, format 씌워서 db 저장할 것.
    private String createMailFormat(String html, Map<String, String> params) {
        Context context = new Context();

        Iterator iterator  = params.keySet().iterator();
        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = params.get(key);
            context.setVariable(key, value);
        }

        return templateEngine.process(html, context);
    }
}
