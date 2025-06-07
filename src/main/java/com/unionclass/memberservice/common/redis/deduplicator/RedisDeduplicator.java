package com.unionclass.memberservice.common.redis.deduplicator;

import java.time.Duration;

public interface RedisDeduplicator {

    boolean isFirstRequest(String key, Duration ttl);
}
