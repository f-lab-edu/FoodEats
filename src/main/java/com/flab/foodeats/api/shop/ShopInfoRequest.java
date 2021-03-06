package com.flab.foodeats.api.shop;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.shop.ShopInfoTarget;

public class ShopInfoRequest {


	@NotBlank(message = "Input Your Category ")
	private String category;
	@NotBlank(message = "Input Your Brand")
	private String brand;
	@NotBlank(message = "Input Your Location")
	private String location;
	@NotBlank(message = "Input Your Phone")
	private String phone;

	public ShopInfoRequest() {
	}

	public ShopInfoRequest(String category, String brand, String location, String phone) {
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

	public ShopInfoTarget toParam(Long shopId) {
		return new ShopInfoTarget(shopId, category, brand, location, phone);
	}
}
