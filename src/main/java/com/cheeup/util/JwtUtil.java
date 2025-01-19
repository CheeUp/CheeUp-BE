package com.cheeup.util;

import com.cheeup.domain.enums.MemberRole;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    // 시간단위 : MS

    private final SecretKey secretKey;


    private final long accessExpirationMillis;

    private final long refreshExpirationMillis;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token.expiration-second}") long accessExpirationMillis,
            @Value("${jwt.refresh-token.expiration-second}") long refreshExpirationMillis) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessExpirationMillis = accessExpirationMillis;
        this.refreshExpirationMillis = refreshExpirationMillis;
    }

    public String getMemberId(String token) {
        return parsePayload(token, "memberId", String.class);
    }

    public List<String> getRoles(String token) {
        return (List<String>) parsePayload(token, "role", List.class);
    }

    private <T> T parsePayload(String token, String key, Class<T> clazz) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .get(key, clazz);
    }

    public String createAccessToken(String memberId, List<MemberRole> roles) {
        return createToken(memberId, roles, accessExpirationMillis);
    }

    public String createRefreshToken(String memberId, List<MemberRole> roles) {
        return createToken(memberId, roles, refreshExpirationMillis);
    }


    private String createToken(String memberId, List<MemberRole> roles, long expiration) {
        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("role", roles.stream().map(Enum::name).toList())
                .issuedAt(generateIssuedAt())
                .expiration(generateTokenExpiration(expiration))
                .signWith(secretKey)
                .compact();
    }

    private Date generateIssuedAt() {
        return Date.from(ZonedDateTime.now().toInstant());

    }

    private Date generateTokenExpiration(long delta) {
        return Date.from(ZonedDateTime.now().plusSeconds(delta).toInstant());
    }

}