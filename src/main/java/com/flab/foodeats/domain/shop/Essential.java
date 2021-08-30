package com.flab.foodeats.domain.shop;

public class Essential {

	private Long shopId;
	private String userId;
	private String category;
	private String brand;
	private String location;
	private String phone;

	public Essential() {
	}

	public Essential(Long shopId, String userId, String category, String brand, String location, String phone) {
		this.shopId = shopId;
		this.userId = userId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	@Override
	public String toString() {
		return "Essential{" +
			"shopId=" + shopId +
			", userId='" + userId + '\'' +
			", category='" + category + '\'' +
			", brand='" + brand + '\'' +
			", location='" + location + '\'' +
			", phone='" + phone + '\'' +
			'}';
	}
}
