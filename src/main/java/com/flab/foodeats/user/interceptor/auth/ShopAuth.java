package com.flab.foodeats.user.interceptor.auth;

public class ShopAuth {
	public static String SHOP_KEY = "ShopLogin";
	private String id;
	private Integer shopId;

	public ShopAuth(String id, Integer shopId) {
		this.id = id;
		this.shopId = shopId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "ShopUserInfo{" +
			"id='" + id + '\'' +
			", shopId=" + shopId +
			'}';
	}
}
