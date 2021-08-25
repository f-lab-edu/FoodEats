package com.flab.foodeats.api.shop;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;

public class DeleteEssentialRequest {

	public DeleteEssentialTarget toParam(String userId) {
		return new DeleteEssentialTarget(userId);
	}


}
