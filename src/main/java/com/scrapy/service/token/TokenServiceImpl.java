package com.scrapy.service.token;

import com.scrapy.service.redis.session.ISessionRedisService;
import com.scrapy.service.redis.RedisService;
import com.scrapy.service.token.provider.JwtProvider;
import com.scrapy.service.token.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final ISessionRedisService sessionRedisServiceImpl;

    private final TokenProvider tokenProvider;

    @Override
    public String generateToken(String userId) {

        if (sessionRedisServiceImpl.get(userId) != null) {
            sessionRedisServiceImpl.delete(userId);
        }

        String token = tokenProvider.generate(userId, ChronoUnit.MINUTES, 30);
        sessionRedisServiceImpl.save(userId, token);

        return token;
    }

    @Override
    public void revokeToken(String userId, String token) {
        if (sessionRedisServiceImpl.get(userId) != null) {
            sessionRedisServiceImpl.delete(userId);
        }
    }

    @Override
    public boolean validateToken(String userId, String token) {

        try {
            if (sessionRedisServiceImpl.hasKey(userId)) {
                Object object = sessionRedisServiceImpl.get(userId);
                // json형태로 형변환.
                // token 값 비교.

            }

            return false;

        } catch (Exception e) {
            // 로그
            return false;
        }
    }
}
