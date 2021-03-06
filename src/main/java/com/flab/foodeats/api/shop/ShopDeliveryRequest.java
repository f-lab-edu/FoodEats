package com.flab.foodeats.api.shop;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.shop.ShopDeliveryTarget;

public class ShopDeliveryRequest {


	@NotBlank(message = "Input Your grade ")
	private String grade;
	@NotBlank(message = "Input Your deliveryMethod")
	private String deliveryMethod;
	@NotBlank(message = "Input Your shopPaymentMethod")
	private String shopPaymentMethod;

	public ShopDeliveryRequest() {
	}

	public ShopDeliveryRequest(String grade, String deliveryMethod, String shopPaymentMethod) {
		this.grade = grade;
		this.deliveryMethod = deliveryMethod;
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getShopPaymentMethod() {
		return shopPaymentMethod;
	}

	public void setShopPaymentMethod(String shopPaymentMethod) {
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public ShopDeliveryTarget toParam(Long shopId) {
		return new ShopDeliveryTarget(shopId, grade, deliveryMethod, shopPaymentMethod);
	}

}
