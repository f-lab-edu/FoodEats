package com.flab.foodeats.shop.model;

import java.util.List;

public class ShopDeliveryLocation {

	private List<ShopDeliveryLocationParam> deliveryParams;

	public ShopDeliveryLocation() {
	}

	public ShopDeliveryLocation(
		List<ShopDeliveryLocationParam> shopDeliveryLocationParams) {
		this.deliveryParams = shopDeliveryLocationParams;
	}

	public List<ShopDeliveryLocationParam> getDeliveryParams() {
		return deliveryParams;
	}
}
