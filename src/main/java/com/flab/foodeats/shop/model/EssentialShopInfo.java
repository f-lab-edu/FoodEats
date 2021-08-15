package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EssentialShopInfo {

	/**
	 * 가맹점 등록
	 * DB : shop_info
	 */
	private int shopId;
	@NotBlank(message = "Input Your Category ")
	private String category;
	@NotBlank(message = "Input Your Brand")
	private String brand;
	@NotBlank(message = "Input Your Location")
	private String location;
	@NotBlank(message = "Input Your Phone")
	private String phone;

	public EssentialShopInfo() {
	}

	public EssentialShopInfo(int shopId, String category, String brand, String location, String phone) {
		this.shopId = shopId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
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
		return "EssentialShopInfo{" +
			"shopId=" + shopId +
			", category='" + category + '\'' +
			", brand='" + brand + '\'' +
			", location='" + location + '\'' +
			", phone='" + phone + '\'' +
			'}';
	}
}
