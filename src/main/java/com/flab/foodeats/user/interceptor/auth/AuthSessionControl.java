package com.flab.foodeats.user.interceptor.auth;

public class AuthSessionControl {
	private static ThreadLocal<String> local = new ThreadLocal<>();

	public static boolean hasAuthentication() {
		return local.get() != null;
	}

	public static String getAuthentication() {
		return local.get();
	}

	public static void setAuthentication(String auth) {
		local.set(auth);
	}

	public static void reset() {
		local.remove();
	}
}