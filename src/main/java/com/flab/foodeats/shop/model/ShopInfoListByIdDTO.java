package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ShopInfoListByIdDTO {

	/**
	 * 가맹점 조회
	 * DB : shop_info
	 */

	@NotNull(message = "Input Your ShopID")
	private int shopId;
	@NotBlank(message = "Input Your Category ")
	private String category;
	@NotBlank(message = "Input Your Brand")
	private String brand;
	@NotBlank(message = "Input Your Location")
	private String location;
	@NotBlank(message = "Input Your Phone")
	private String phone;


	public ShopInfoListByIdDTO() {
	}

	public ShopInfoListByIdDTO(int shopId, String category, String brand, String location, String phone) {
		this.shopId = shopId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}

	public int getShopId() {
		return shopId;
	}

	public String getCategory() {
		return category;
	}

	public String getBrand() {
		return brand;
	}

	public String getLocation() {
		return location;
	}

	public String getPhone() {
		return phone;
	}
}
