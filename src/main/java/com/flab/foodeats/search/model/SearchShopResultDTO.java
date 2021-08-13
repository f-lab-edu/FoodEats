package com.flab.foodeats.search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchShopResultDTO {

	@JsonProperty
	private int shopId;
	@JsonProperty
	private String category;
	@JsonProperty
	private String brand;
	@JsonProperty
	private String location;
	@JsonProperty
	private String phone;

	public SearchShopResultDTO() {
	}

	public SearchShopResultDTO(int shopId, String category, String brand, String location, String phone) {
		this.shopId = shopId;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}
}
