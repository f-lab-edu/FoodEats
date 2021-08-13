package com.flab.foodeats.shop.model;

public class ShopDeliveryLocationParam {

	private String deliveryLocation;
	private int deliveryLocationTip;

	public ShopDeliveryLocationParam() {
	}

	public ShopDeliveryLocationParam(String deliveryLocation, int deliveryLocationTip) {
		this.deliveryLocation = deliveryLocation;
		this.deliveryLocationTip = deliveryLocationTip;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	public int getDeliveryLocationTip() {
		return deliveryLocationTip;
	}
}
