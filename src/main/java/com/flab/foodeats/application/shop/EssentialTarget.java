package com.flab.foodeats.application.shop;

import com.flab.foodeats.domain.shop.Essential;

public class EssentialTarget {

	private String shopId;
	private String category;
	private String brand;
	private String location;
	private String phone;

	public EssentialTarget() {
	}

	public EssentialTarget(String shopId, String category, String brand, String location, String phone) {
		this.shopId = shopId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
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

	public Essential toEntity(){
		return new Essential(shopId,category,brand,location,phone);
	}
}
