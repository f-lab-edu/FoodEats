package com.flab.foodeats.application.shop;

import com.flab.foodeats.domain.shop.Essential;

public class EssentialTarget {

	private String userId;
	private String category;
	private String brand;
	private String location;
	private String phone;

	public EssentialTarget() {
	}

	public EssentialTarget(String userId, String category, String brand, String location, String phone) {
		this.userId = userId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Essential changeToEntityForRegister(){
		return new Essential(0l, userId,category,brand,location,phone);
	}

	public Essential changeToEntityForUpdate(Long shopId){
		return new Essential(shopId, userId,category,brand,location,phone);
	}
}
