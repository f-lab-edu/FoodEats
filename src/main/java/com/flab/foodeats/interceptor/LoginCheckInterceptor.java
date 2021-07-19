package com.flab.foodeats.interceptor;

import static com.flab.foodeats.model.ResponseStatus.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.foodeats.SessionConst;
import com.flab.foodeats.model.ResponseResult;

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
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		String requestURI = request.getRequestURI();
		log.info("인증 체크 인터셉터 실행 {}", requestURI);

		HttpSession session = request.getSession(false);
		HandlerMethod handlerMethod = (HandlerMethod)handler;

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ObjectMapper mapper = new ObjectMapper();

		// 어노테이션 적용 부분
		UserLoginAuth filter = handlerMethod.getMethod().getAnnotation(UserLoginAuth.class);

		// 어노테이션 정보가 없다면 작동 x
		if (filter != null) {
			if (session == null || session.getAttribute(SessionConst.LOGIN) == null) {
				ResponseResult msg = new ResponseResult(FAIL, "no Authorized");
				response.getWriter().write(mapper.writeValueAsString(msg));
				return false;
			}
			return true;
		}
		ResponseResult msg = new ResponseResult(FAIL, "no Annotation");
		response.getWriter().write(mapper.writeValueAsString(msg));
		return false;
	}
}
