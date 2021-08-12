package com.flab.foodeats.user.interceptor.auth;

public class ShopAuthSessionControl {
	private static ThreadLocal<ShopAuth> local = new ThreadLocal<>();

	public static ShopAuth getAuthentication() {
		return local.get();
	}

	public static void setAuthentication(ShopAuth shopAuth) {
		local.set(shopAuth);
	}

	public static void reset() {
		local.remove();
	}
}
