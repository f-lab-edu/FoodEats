package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ConvenientShopInfo {

	private int shopId;
	@NotBlank(message = "Input Your grade ")
	private String grade;
	@NotBlank(message = "Input Your deliveryMethod")
	private String deliveryMethod;
	@NotBlank(message = "Input Your shopPaymentMethod")
	private String shopPaymentMethod;

	public ConvenientShopInfo(int shopId, String grade, String deliveryMethod, String shopPaymentMethod) {
		this.shopId = shopId;
		this.grade = grade;
		this.deliveryMethod = deliveryMethod;
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
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

	@Override
	public String toString() {
		return "ConvenientShopInfo{" +
			"shopId=" + shopId +
			", grade='" + grade + '\'' +
			", deliveryMethod='" + deliveryMethod + '\'' +
			", shopPaymentMethod='" + shopPaymentMethod + '\'' +
			'}';
	}
}
