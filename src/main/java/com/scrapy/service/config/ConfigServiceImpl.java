package com.scrapy.service.config;

import com.scrapy.common.enums.CommonEnum;
import com.scrapy.common.log.Log;
import com.scrapy.model.config.Configuration;
import com.scrapy.repository.config.ConfigRepository;
import com.scrapy.service.redis.config.IConfigRedisService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements IConfigService {

    @Value("${spring.data.redis.enabled}")
    private boolean isRedis;

    private final IConfigRedisService configRedisService;

    private final IConfigContainerService configContainerService;

    private final ConfigRepository configRepository;


    @PostConstruct
    private void initialize() {
        Log.info("configuration initialize...");
        update();
    }

    @Override
    public void update() {

        LocalDateTime latestUpdateTime = getLatestUpdateTime();
        if (latestUpdateTime.isBefore(LocalDateTime.now())) {
            Log.info("configuration update. latestUpdateTime : {}", latestUpdateTime);

            // 최근 업데이트 이후, 업데이트 된 것이 있으면 전부 업데이트.
            if (!updateConfigurationAll()) {
                Log.error("configuration update fail. latestUpdateTime : {}", latestUpdateTime);
                // 메일 발송.
            }
            Log.info("configuration update complete. latestUpdateTime : {}", latestUpdateTime);
        }
    }

    private LocalDateTime getLatestUpdateTime() {
        return isRedis ? (LocalDateTime) configRedisService.getLatestConfigDate() : (LocalDateTime) configContainerService.getLatestConfigDate();
    }

    private boolean updateConfigurationAll() {
        // 전체 config 가져오기.
        List<Configuration> configurationList = configRepository.findAll().stream().map(m-> new Configuration(
                                                                                                    m.getSeq(), m.getCategory(), m.getCode(), m.getCodeValue(), m.getCodeParam(),
                                                                                                    m.isEnable(), m.getDescription(), m.getCreateTime(), m.getUpdateTime()))
                                                                                            .collect(Collectors.toList());
        // redis에 적재.
        return isRedis ? updateToRedis(configurationList) : updateToMap(configurationList);
    }

    private boolean updateToRedis(List<Configuration> list) {
        boolean isSuccess = true;
        String code = null;

        for (Configuration configuration : list) {
            try {
                code = createSaveRedisCode(configuration.getCategory(), configuration.getCode());
                configRedisService.save(CommonEnum.code_database_scrapy.getCode().concat(code), configuration);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Log.error("configuration update error, code={}, e={}", code, e.getMessage());
                isSuccess = false;
                continue;
            }
        }

        // 최신 업데이트 날짜 갱신.
        configRedisService.saveLatestConfigDate(LocalDateTime.now());
        return isSuccess;
    }

    private boolean updateToMap(List<Configuration> list) {
        boolean isSuccess = true;
        String code = "";

        for (Configuration configuration : list) {
            try {
                code = createSaveRedisCode(configuration.getCategory(), configuration.getCode());
                configContainerService.save(CommonEnum.code_database_scrapy.getCode().concat(code), configuration);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Log.error("configuration update error, code={}, e={}", code, e.getMessage());
                isSuccess = false;
                continue;
            }
        }

        // 최신 업데이트 날짜 갱신.
        configContainerService.saveLatestConfigDate(LocalDateTime.now());
        return isSuccess;
    }

    @Override
    public Configuration get(String category, String code) {

        if (isRedis) {
            String configCode = createSaveRedisCode(category, code);
            return (Configuration) configRedisService.get(CommonEnum.code_database_scrapy.getCode().concat(configCode));
        }
        return null;
    }

    private String createSaveRedisCode(String category, String code) {
        return category.concat("_").concat(code);
    }
}
