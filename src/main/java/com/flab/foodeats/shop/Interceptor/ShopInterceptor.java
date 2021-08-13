package com.flab.foodeats.shop.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.user.interceptor.auth.AuthErrorCheck;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.interceptor.auth.ShopAuth;
import com.flab.foodeats.user.model.code.ErrorUserCode;

@Component
public class ShopInterceptor implements HandlerInterceptor {

	private AuthErrorCheck authErrorCheck;

	public ShopInterceptor(AuthErrorCheck authErrorCheck) {
		this.authErrorCheck = authErrorCheck;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		//Response
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();

		//Session
		HttpSession session = request.getSession(false);

		try {
			ShopAuth shopAuth = (ShopAuth)session.getAttribute(ShopAuth.SHOP_KEY);
			AuthSessionControl.setShopAuthentication(shopAuth);
		} catch (Exception e) {
			throw new Exception(ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
		}

		if (!authErrorCheck.sessionNullCheck(session)) {
			ApiResponse msg = new ApiResponse(StatusCode.FAIL, ErrorUserCode.SESSION_NO_AUTHORIZED.getMessage());
			response.getWriter().write(mapper.writeValueAsString(msg));
			return false;
		}

		return true;
	}
}