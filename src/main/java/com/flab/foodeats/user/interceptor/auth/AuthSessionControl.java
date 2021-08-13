package com.flab.foodeats.user.interceptor.auth;

public class AuthSessionControl {
	// 소비자
	private static ThreadLocal<String> local = new ThreadLocal<>();

	public static String getAuthentication() {
		return local.get();
	}

	public static void setAuthentication(String auth) {
		local.set(auth);
	}

	public static void reset() {
		local.remove();
	}

	// 가맹점
	private static ThreadLocal<ShopAuth> shopLocal = new ThreadLocal<>();

	public static ShopAuth getShopAuthentication() {
		return shopLocal.get();
	}

	public static void setShopAuthentication(ShopAuth shopAuth) {
		shopLocal.set(shopAuth);
	}

	public static void shopReset() {
		shopLocal.remove();
	}
}