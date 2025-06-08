package com.unionclass.memberservice.common.redis.deduplicator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisDeduplicatorImpl implements  RedisDeduplicator {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean isFirstRequest(String key, Duration ttl) {
        try {
            return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, "LOCKED", ttl));
        } catch (Exception e) {
            log.error("Redis 중복 요청 체크 실패 - key: {}", key, e);
            return true;
        }
    }
}
