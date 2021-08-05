
package com.flab.foodeats.user.aop;

import lombok.RequiredArgsConstructor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.flab.foodeats.user.interceptor.auth.Auth;

@RequiredArgsConstructor
@Aspect     // AOP Aspect
// @Component
public class LoginAuth {

	@Around("@annotation(AopAuth)") // 어노테이션과 Aspect 연결
	public Object LoginAroundAuth(ProceedingJoinPoint pjp) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute(Auth.KEY) == null) {
			System.out.println(" 오류 발생 추가 하기 ");
		}
		Object reval = pjp.proceed();

		return reval;
	}

	@Before("@annotation(AopAuth)")
	public void doSomethingBefore() {
		//System.out.println("메소드 시작전 !");
	}

	@After("@annotation(AopAuth)")
	public void doSomethingAfter() {
		//System.out.println("메소드 시작후 !");
	}

}
