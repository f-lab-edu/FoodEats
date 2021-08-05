package com.flab.foodeats.user.interceptor.auth;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class AuthErrorCheck {

	public boolean sessionNullCheck(HttpSession session) {
		if (session == null) {
			return false;
		}
		return true;
	}

	public boolean authAnnotationNullCheck(AuthPreHandler filter) {
		if (filter == null) {
			return false;
		}
		return true;
	}
}
