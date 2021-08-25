package com.flab.foodeats.api.shop.dto;

import javax.validation.constraints.NotBlank;

import com.flab.foodeats.application.shop.RegisterEssentialTarget;

public class RegisterEssentialRequest {

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

	public RegisterEssentialRequest() {
	}

	public RegisterEssentialRequest(String category, String brand, String location, String phone) {
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

	public RegisterEssentialTarget toParam(String userId) {
		return new RegisterEssentialTarget(userId, category, brand, location, phone);
	}
}
