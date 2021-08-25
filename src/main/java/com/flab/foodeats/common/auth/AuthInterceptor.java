package com.flab.foodeats.common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.flab.foodeats.common.response.ErrorUserCode;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		AuthRequired authRequired = getAuthRequired(handler);
		AuthUsed authUsed = getAuthUsed(handler);

		if (authRequired == null && authUsed == null) {
			return true;
		}

		AuthInfo authInfo = getAuthInfo(request);
		validateRole(authRequired, authInfo);
		AuthInfoAttributeUtils.setAuthInfoAttributes(request, authInfo);

		return true;
	}

	private void validateRole(AuthRequired authRequired, AuthInfo authInfo) throws Exception {
		if (authRequired.role() != authInfo.getUserType()) {

			throw new Exception(ErrorUserCode.AUTH_ROLE_NOT_MATCH.getMessage());
		}
	}


	private AuthInfo getAuthInfo(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if (null == session) {
			throw new Exception(ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
		}
		return (AuthInfo)session.getAttribute(AuthInfo.AUTH_KEY);
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

