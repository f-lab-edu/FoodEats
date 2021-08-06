package com.flab.foodeats.user.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.foodeats.user.interceptor.auth.Auth;
import com.flab.foodeats.user.interceptor.auth.AuthErrorCheck;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.interceptor.auth.AuthPreHandler;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.model.code.ErrorUserCode;
import com.flab.foodeats.global.StatusCode;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private AuthErrorCheck authErrorCheck;

	public AuthInterceptor(AuthErrorCheck authErrorCheck) {
		this.authErrorCheck = authErrorCheck;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		//Response
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();

		//Annotation
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		AuthPreHandler filter = handlerMethod.getMethod().getAnnotation(AuthPreHandler.class);

		//Session
		HttpSession session = request.getSession(false);
		try {
			String auth = (String)session.getAttribute(Auth.KEY);
			AuthSessionControl.setAuthentication(auth);
		} catch (Exception e) {
			throw new Exception(ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
		}

		if (!authErrorCheck.sessionNullCheck(session)) {
			ApiResponse msg = new ApiResponse(StatusCode.FAIL, ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
			response.getWriter().write(mapper.writeValueAsString(msg));
			return false;
		}

		if (!authErrorCheck.authAnnotationNullCheck(filter)) {
			ApiResponse msg = new ApiResponse(StatusCode.FAIL, ErrorUserCode.AUTH_NO_ANNOTATION.getMessage());
			response.getWriter().write(mapper.writeValueAsString(msg));
			return false;
		}
		return true;
	}

}
