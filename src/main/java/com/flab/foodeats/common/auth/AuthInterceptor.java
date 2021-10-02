package com.flab.foodeats.common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.flab.foodeats.common.response.ErrorUserCode;
import com.flab.foodeats.common.util.token.TokenService;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private final TokenService tokenService;

	public AuthInterceptor(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		AuthRequired authRequired = getAuthRequired(handler);
		AuthUsed authUsed = getAuthUsed(handler);

		if (authRequired == null && authUsed == null) {
			return true;
		}

		String header = request.getHeader(AuthConstants.AUTH_HEADER);
		tokenService.validateHeader(header);

		String token = tokenService.validateToken(header);
		tokenService.validateTokenExpiration(token);

		AuthInfo authInfo = tokenService.getAuthInfo(token);
		validateRole(authRequired, authInfo);
		AuthInfoAttributeUtils.setAuthInfoAttributes(request, authInfo);

		return true;
	}

	private void validateRole(AuthRequired authRequired, AuthInfo authInfo) throws Exception {
		if (authRequired.role() != authInfo.getUserType()) {
			throw new Exception(ErrorUserCode.AUTH_ROLE_NOT_MATCH.getMessage());
		}
	}

	private AuthRequired getAuthRequired(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		return handlerMethod.getMethod().getAnnotation(AuthRequired.class);
	}

	private AuthUsed getAuthUsed(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		return handlerMethod.getMethod().getAnnotation(AuthUsed.class);
	}
}
