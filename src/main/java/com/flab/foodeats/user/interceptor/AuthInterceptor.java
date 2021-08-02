package com.flab.foodeats.user.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.foodeats.user.interceptor.auth.Auth;
import com.flab.foodeats.user.interceptor.auth.AuthErrorCheck;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.interceptor.auth.AuthPreHandler;
import com.flab.foodeats.user.model.ApiResponse;
import com.flab.foodeats.user.model.code.ErrorUserCode;
import com.flab.foodeats.user.model.code.StatusUserCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private AuthErrorCheck authErrorCheck;

	public AuthInterceptor(AuthErrorCheck authErrorCheck) {
		this.authErrorCheck = authErrorCheck;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		//Annotation
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		AuthPreHandler filter = handlerMethod.getMethod().getAnnotation(AuthPreHandler.class);

		//Session
		HttpSession session = request.getSession(false);
		try {
			String auth = (String)session.getAttribute(Auth.KEY);
			AuthSessionControl.setAuthentication(auth);
		} catch (ClassCastException e) {
			throw new ClassCastException(ErrorUserCode.SESSION_CAST_UNMATCH.getMessage());
		}

		//Response
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();

		//ErrorCheck
		if(!authErrorCheck.SessionCheck(session)){
			ApiResponse msg = new ApiResponse(StatusUserCode.FAIL, ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
			response.getWriter().write(mapper.writeValueAsString(msg));
			return false;
		}
		if(!authErrorCheck.AnnotationCheck(filter)){
			ApiResponse msg = new ApiResponse(StatusUserCode.FAIL, ErrorUserCode.AUTH_NO_ANNOTATION.getMessage());
			response.getWriter().write(mapper.writeValueAsString(msg));
			return false;
		}
		return true;
	}
}
