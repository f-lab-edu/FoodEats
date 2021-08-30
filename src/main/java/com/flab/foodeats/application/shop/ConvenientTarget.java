package com.flab.foodeats.application.shop;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.domain.shop.Convenient;
import com.flab.foodeats.domain.shop.Essential;

public class ConvenientTarget {


	private String grade;
	private String deliveryMethod;
	private String shopPaymentMethod;

	public ConvenientTarget() {
	}

	public ConvenientTarget(String grade, String deliveryMethod, String shopPaymentMethod) {
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

	public Convenient toEntity(Long shopId){
		return new Convenient(shopId,grade,deliveryMethod,shopPaymentMethod);
	}
}
