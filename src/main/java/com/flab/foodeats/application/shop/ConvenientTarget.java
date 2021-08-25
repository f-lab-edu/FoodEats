package com.flab.foodeats.application.shop;

import com.flab.foodeats.domain.shop.Convenient;

public class ConvenientTarget {

	private String shopId;
	private String grade;
	private String deliveryMethod;
	private String shopPaymentMethod;

	public ConvenientTarget() {
	}

	public ConvenientTarget(String shopId, String grade, String deliveryMethod, String shopPaymentMethod) {
		this.shopId = shopId;
		this.grade = grade;
		this.deliveryMethod = deliveryMethod;
		this.shopPaymentMethod = shopPaymentMethod;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
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

	public Convenient toEntity(){
		return new Convenient(shopId,grade,deliveryMethod,shopPaymentMethod);
	}
}
