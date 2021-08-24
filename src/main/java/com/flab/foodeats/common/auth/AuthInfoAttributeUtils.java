package com.flab.foodeats.common.auth;

import javax.servlet.http.HttpServletRequest;

public class AuthInfoAttributeUtils {

	public static void setAuthInfoAttributes(HttpServletRequest request, AuthInfo authInfo) {
		request.setAttribute(AuthInfo.AUTH_KEY, authInfo);
	}

	public static AuthInfo getAuthInfoAttributes(HttpServletRequest request) {
		return (AuthInfo)request.getAttribute(AuthInfo.AUTH_KEY);
	}
}