package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotBlank;

public class EssentialShopInfo {

	/**
	 * 가맹점 등록
	 * DB : shop_info
	 */

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

	public EssentialShopInfo(String category, String brand, String location, String phone) {
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
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
}
