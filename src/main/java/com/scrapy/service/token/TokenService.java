package com.scrapy.service.token;

public interface TokenService {

    String generateToken(String userId);

    void revokeToken(String userId, String token);

    boolean validateToken(String userId, String token);
}
