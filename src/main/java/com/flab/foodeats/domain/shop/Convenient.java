package com.flab.foodeats.domain.shop;

public class Convenient {
	private Long shopId;
	private String grade;
	private String deliveryMethod;
	private String shopPaymentMethod;

	public Convenient(){

	}

	public Convenient(Long shopId, String grade, String deliveryMethod, String shopPaymentMethod) {
		this.shopId = shopId;
		this.grade = grade;
		this.deliveryMethod = deliveryMethod;
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
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
}
