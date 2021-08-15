package com.flab.foodeats.menu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuOptionParam {

	@JsonProperty
	private int menuOptionId;

	@JsonProperty
	@NotBlank(message = "Input MenuOptionName ")
	private String menuOptionName;

	@JsonProperty
	@NotNull(message = "Input MenuOptionPrice ")
	private int menuOptionPrice;

	public MenuOptionParam() {
	}

	public MenuOptionParam(int menuOptionId, String menuOptionName, int menuOptionPrice) {
		this.menuOptionId = menuOptionId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public int getMenuOptionId() {
		return menuOptionId;
	}

	public String getMenuOptionName() {
		return menuOptionName;
	}

	public int getMenuOptionPrice() {
		return menuOptionPrice;
	}
}
