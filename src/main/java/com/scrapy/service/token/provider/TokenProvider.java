package com.scrapy.service.token.provider;

import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public interface TokenProvider {
    String generate(String subject, ChronoUnit unit, int exp);

    boolean validate(String token);

    Date getExpiration(ChronoUnit unit, int exp);

    // access token 1 hours (short time)
    // refresh token 3 days (long  time)
    // login -> generate refresh token
    // logout -> expire refresh token

    // required method
    // stored refresh token
    // invalid refresh token
    // validate refresh token

}
