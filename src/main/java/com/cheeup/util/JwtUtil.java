package com.cheeup.util;

import com.cheeup.constant.CookieConstant;
import com.cheeup.domain.enums.MemberRole;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {


    private SecretKey secretKey;
    private final Long TTL = CookieConstant.ACCESS_TOKEN_EXPIRE_SECOND;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getMemberId(String token) {
        return parsePayload(token, "memberId", String.class);
    }

    public List<String> getRoles(String token) {
        parsePayload(token, "", List.class);
        return (List<String>) parsePayload(token, "role", List.class);
    }

    private <T> T parsePayload(String token, String key, Class<T> clazz) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .get(key, clazz);
    }

    public String createToken(String memberId, List<MemberRole> roles) {
        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("role", roles.stream().map(Enum::name).toList())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TTL))
                .signWith(secretKey)
                .compact();
    }


    public String createToken(String memberId, String role) {
        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("role", List.of(role))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TTL))
                .signWith(secretKey)
                .compact();
    }

    public String createToken(String memberId, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("role", authorities.stream().map(GrantedAuthority::getAuthority))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TTL))
                .signWith(secretKey)
                .compact();
    }

}

//    public boolean isExpired(String token) {
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
//    }
