package com.flab.foodeats.api.shop;

import com.flab.foodeats.application.shop.DeleteShopInfoTarget;

public class DeleteShopInfoRequest {

	public DeleteShopInfoTarget toParam(Long shopId) {
		return new DeleteShopInfoTarget(shopId);
	}
}
