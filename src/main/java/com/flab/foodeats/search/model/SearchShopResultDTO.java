package com.flab.foodeats.search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchShopResultDTO {

	// 후에 수정 필요 -> db의 변수명과 불일치 시 binding이 안됨.
	@JsonProperty
	private int shop_id;
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

	public SearchShopResultDTO(int shop_id, String category, String brand, String location, String phone) {
		this.shop_id = shop_id;
		this.category = category;
		this.brand = brand;
		this.location = location;
		this.phone = phone;
	}
}
