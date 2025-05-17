package com.scrapy.service.schedule.mail;

import com.scrapy.common.enums.ConfigEnum;
import com.scrapy.common.log.Log;
import com.scrapy.entity.mail.MailScheduleEntity;
import com.scrapy.model.mail.MailMessage;
import com.scrapy.model.mail.MailSchedule;
import com.scrapy.repository.mail.MailScheduleRepository;
import com.scrapy.service.mail.IMailService;
import com.scrapy.service.redis.config.IConfigRedisService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailScheduleServiceImpl implements IMailScheduleService {

    private final IConfigRedisService configRedisService;

    private final MailScheduleRepository mailScheduleRepository;

    private  final IMailService mailService;

    @Value("${spring.mail.properties.mail.max-attempt}")
    private int maxAttempt;

    @Value("${spring.data.redis.enabled}")
    private boolean isRedis;

    @PostConstruct
    private void initialize() {
        if (isRedis && configRedisService.hasKey(ConfigEnum.code_config_mail_properties_max_attempt.getCode())) {
            this.maxAttempt = Integer.parseInt((String) configRedisService.get(ConfigEnum.code_config_mail_properties_max_attempt.getCode()));
        }
    }

    @Override
    public void execute() {

        // 목록 가져오기
        List<MailSchedule> list = getReservedMail();

        for (MailSchedule mailSchedule : list) {

            try {
                MailMessage mailMessage = MailMessage.builder()
                        .from(mailSchedule.getFromMail())
                        .to(mailSchedule.getToMail())
                        .subject(mailSchedule.getTitle())
                        .message(mailSchedule.getMessage())
                        .build();

                boolean isSend = mailService.send(mailMessage);

/*
                updateSendAttemptsCount(mailSchedule.getSeq(), mailSchedule.getSendAttempts() + 1);
                if (isSend == true) {
                    //  완료 처리.
                    updateIsSend(mailSchedule.getSeq(), mailSchedule.isSend());
                }
*/

            } catch (MessagingException e) {
                Log.error("exception occurred during scheduled email sending, title={}, e={}", mailSchedule.getTitle(), e.getMessage());
                continue;
            }
        }
    }

    private List<MailSchedule> getReservedMail() {

        return mailScheduleRepository.findBySendAttempts(maxAttempt)
                .stream().map(m-> new MailSchedule( m.getSeq(),
                        m.getToMail(),
                        m.getFromMail(),
                        m.getTitle(),
                        m.getMessage(),
                        m.getReservedTime(),
                        m.isSend(),
                        m.getSendAttempts(),
                        m.getUpdateTime(),
                        m.getCreateTime())
                ).collect(Collectors.toList());
    }

    @Transactional
    public void updateSendAttemptsCount(long seq, int sendAttemps) {

        // 영속성 컨텍스트는 트랜잭션이 끝나기 전에 flush
        // flush 에서는 영속성된 컨텍스트가 처음 상태와 같은지 비교. - 더티체킹
        // 이 과정에서 변경된 Entity는 update DB commit
        Optional<MailScheduleEntity> optional = mailScheduleRepository.findById(seq);
        if (optional.isPresent()) {
            MailScheduleEntity mailScheduleEntity = optional.get();
            mailScheduleEntity.updateSendAttemptsCount(sendAttemps);
        }
    }

    @Transactional
    public void updateIsSend(long seq, boolean isSend) {

        Optional<MailScheduleEntity> optional = mailScheduleRepository.findById(seq);
        if (optional.isPresent()) {
            MailScheduleEntity mailScheduleEntity = optional.get();
            mailScheduleEntity.updateIsSend(isSend);
        }
    }
}
