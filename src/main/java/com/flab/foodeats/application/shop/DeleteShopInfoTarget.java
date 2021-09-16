package com.flab.foodeats.application.shop;

public class DeleteShopInfoTarget {

	private Long shopId;

	public DeleteShopInfoTarget() {

	}

	public DeleteShopInfoTarget(Long shopId) {
		this.shopId = shopId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
