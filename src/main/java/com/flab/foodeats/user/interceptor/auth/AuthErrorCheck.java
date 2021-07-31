package com.flab.foodeats.user.interceptor.auth;

public class AuthErrorCheck {

	public boolean SessionCheck(Object session) {
		if (session == null) {
			return false;
		}
		return true;
	}

	public boolean AnnotationCheck(AuthPreHandler filter) {
		if (filter == null) {
			return false;
		}
		return true;
	}
}
