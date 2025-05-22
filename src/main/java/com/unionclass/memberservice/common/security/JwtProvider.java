package com.unionclass.memberservice.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final Environment env;
    private static final String BEARER_PREFIX = "Bearer ";

    public String generateAccessToken(Authentication authentication) {

        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("jwt.token.access-expire-time", Long.class));

        return BEARER_PREFIX +
                Jwts.builder()
                        .signWith(getSignKey())
                        .claims(claims)
                        .issuedAt(now)
                        .expiration(expiration)
                        .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(env.getProperty("jwt.secret-key").getBytes());
    }

    public String validateAndGetMemberUuid(String token) throws IllegalStateException {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("토큰에 담긴 유저 정보가 없습니다.");
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllclaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllclaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}