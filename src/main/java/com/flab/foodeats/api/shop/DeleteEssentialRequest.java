package com.flab.foodeats.api.shop;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;

public class DeleteEssentialRequest {

	public DeleteEssentialTarget toParam(Long shopId) {
		return new DeleteEssentialTarget(shopId);
	}
}
