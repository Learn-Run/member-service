package com.unionclass.memberservice.common.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    public void setValueWithTTL(String key, Object value, long ttlSeconds, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttlSeconds, timeUnit);
    }
}