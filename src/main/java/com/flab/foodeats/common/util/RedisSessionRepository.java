package com.flab.foodeats.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.flab.foodeats.common.auth.AuthInfo;

@Component
public class RedisSessionRepository {

	private final RedisTemplate<String, AuthInfo> redisTemplate;

	public RedisSessionRepository(
		RedisTemplate<String, AuthInfo> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void save(String sessionId, AuthInfo authInfo) {
		redisTemplate.opsForValue().set(sessionId, authInfo);
	}

	public AuthInfo findBySessionId(String sessionId) {
		return redisTemplate.opsForValue().get(sessionId);
	}

	public void remove(String sessionId) {
		redisTemplate.delete(sessionId);
	}

}
