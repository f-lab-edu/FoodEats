package com.flab.foodeats.application.shop;

import com.flab.foodeats.domain.shop.Shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopInfoTarget {

	private Long shopId;
	private String category;
	private String brand;
	private String location;
	private String phone;

	public ShopInfoTarget() {
	}

	public ShopInfoTarget(Long shopId, String category, String brand, String location, String phone) {
		this.shopId = shopId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}

	public Shop toEntity() {
		return Shop.builder()
			.shopId(shopId)
			.category(category)
			.brand(brand)
			.location(location)
			.phone(phone)
			.build();
	}
}
