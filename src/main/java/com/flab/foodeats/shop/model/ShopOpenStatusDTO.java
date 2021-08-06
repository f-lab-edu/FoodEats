package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotNull;

public class ShopOpenStatusDTO {

	/**
	 * 가맹점 오픈 / 마감
	 * DB : shop_convenience
	 */

	@NotNull(message = "Input Your ShopID")
	private int shopId;

	public ShopOpenStatusDTO(){

	}

	public ShopOpenStatusDTO(int shopId) {
		this.shopId = shopId;
	}

	public int getShopId() {
		return shopId;
	}
}
