package com.scrapy.service.token.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider implements TokenProvider {

    private final String base64Key = "f8mP1aaT5TjggkE5l/Xly43ph+vvlFHCcyp3zRQi83M=";
    private final String issuer = "scrapy-server";
    @Override
    public String generate(String subject, ChronoUnit unit, int ttl) {

        long now = System.currentTimeMillis();
        Date issueDate = new Date(now);

        Date expiration = getExpiration(unit, ttl);

        Claims claims = Jwts.claims();
        claims.put("userId", subject);
        claims.put("role", "user");

        return Jwts.builder()
                .setSubject(subject) // 주체 userId
                .setIssuer(issuer) // 발급자
                .setIssuedAt(issueDate) // 발행시간
                .setExpiration(expiration) // 만료시간
                .setClaims(claims)
                .signWith(restoreSecreyKey(base64Key), SignatureAlgorithm.HS256) // 서명 알고릐즘과 비밀키
                .compact();
    }

    @Override
    public boolean validate(String token) {

        try {
            Claims claims = parseJwt(token);
            // claims 출력.
        } catch (ExpiredJwtException e) {
            // 토큰 만료
            return false;
        } catch (SignatureException e){
            // 서명 오류
            return false;
        } catch (JwtException e) {
            // 일반적인 JWT 오류
            return false;
        }
        return true;
    }

    @Override
    public Date getExpiration(ChronoUnit unit, int exp) {

        Instant instant = Instant.now().plus(exp, unit);
        return Date.from(instant);
    }

    private Claims parseJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(restoreSecreyKey(base64Key))
                .build()
                .parseClaimsJws(token)
                .getBody(); // 유효 토큰이 아닐 경우, JwtException 발생.
    }

    private Key key() {
        return Keys.hmacShaKeyFor(base64Key.getBytes());
    }



    private Key generateRandomKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String getRandomSecretKey() {
        return Base64.getEncoder().encodeToString(generateRandomKey().getEncoded());
    }

    public Key restoreSecreyKey(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}

