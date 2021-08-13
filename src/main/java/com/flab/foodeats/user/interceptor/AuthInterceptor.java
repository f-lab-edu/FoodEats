package com.flab.foodeats.user.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.foodeats.user.interceptor.auth.Auth;
import com.flab.foodeats.user.interceptor.auth.AuthErrorCheck;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.interceptor.auth.AuthRequired;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.interceptor.auth.ShopAuthSessionControl;
import com.flab.foodeats.user.interceptor.auth.ShopAuth;
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
		AuthRequired filter = handlerMethod.getMethod().getAnnotation(AuthRequired.class);

		//Session
		HttpSession session = request.getSession(false);

		try {
			if (request.getRequestURI().equals("/user/consumer/update") || request.getRequestURI()
				.equals("/user/consumer/delete")) {
				String auth = "";
				auth = (String)session.getAttribute(Auth.CUNSUMER_KEY);
				AuthSessionControl.setAuthentication(auth);
			}

			if (request.getRequestURI().equals("/user/shop/update") || request.getRequestURI()
				.equals("/user/shop/delete")) {
				ShopAuth shopAuth = (ShopAuth)session.getAttribute(ShopAuth.SHOP_KEY);
				AuthSessionControl.setShopAuthentication(shopAuth);

			}
		} catch (Exception e) {
			throw new Exception(ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
		}

		if (!authErrorCheck.authAnnotationNullCheck(filter)) {
			ApiResponse msg = new ApiResponse(StatusCode.FAIL, ErrorUserCode.AUTH_NO_ANNOTATION.getMessage());
			response.getWriter().write(mapper.writeValueAsString(msg));
			return false;
		}
		return true;
	}

}
