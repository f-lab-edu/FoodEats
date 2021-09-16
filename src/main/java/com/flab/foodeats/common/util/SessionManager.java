package com.flab.foodeats.common.util;

import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.response.ErrorUserCode;

@Component
public class SessionManager {

	public static final String SESSION_COOKIE_NAME = "JSESSIONID";

	private final RedisSessionRepository redisSessionRepository;

	public SessionManager(RedisSessionRepository redisSessionRepository) {
		this.redisSessionRepository = redisSessionRepository;
	}

	public void createSession(AuthInfo value, HttpServletResponse response) {
		String sessionId = UUID.randomUUID().toString();
		redisSessionRepository.save(sessionId, value);

		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		response.addCookie(mySessionCookie);
	}

	public AuthInfo getSession(HttpServletRequest request) throws Exception {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		AuthInfo authInfo = redisSessionRepository.findBySessionId(sessionCookie.getValue());

		if (authInfo == null) {
			throw new Exception(ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
		}
		return authInfo;
	}

	public void expire(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if (sessionCookie != null) {
			redisSessionRepository.remove(sessionCookie.getValue());
		}
	}

	public Cookie findCookie(HttpServletRequest request, String cookieName) {
		if (request.getCookies() == null) {
			return null;
		}
		return Arrays.stream(request.getCookies())
			.filter(cookie -> cookie.getName().equals(cookieName))
			.findAny()
			.orElse(null);
	}
}
