package com.flab.foodeats.domain.shop;

public class Essential {

	private String shopId;
	private String category;
	private String brand;
	private String location;
	private String phone;

	public Essential() {
	}

	public Essential(String shopId, String category, String brand, String location, String phone) {
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

	@Override
	public String toString() {
		return "Essential{" +
			"shopId='" + shopId + '\'' +
			", category='" + category + '\'' +
			", brand='" + brand + '\'' +
			", location='" + location + '\'' +
			", phone='" + phone + '\'' +
			'}';
	}
}
