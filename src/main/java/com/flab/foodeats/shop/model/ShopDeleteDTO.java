package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotNull;

public class ShopDeleteDTO {

	/**
	 * 가맹점 삭제
	 * DB : shop_info
	 */

	@NotNull(message = "Input Your ShopID")
	private int shopId;

	public ShopDeleteDTO() {
	}

	public ShopDeleteDTO(int shopId) {
		this.shopId = shopId;
	}

	public int getShopId() {
		return shopId;
	}
}
