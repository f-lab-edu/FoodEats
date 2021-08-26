package com.flab.foodeats.domain.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuOption {

	@JsonProperty
	private int menuId;
	@JsonProperty
	private int menuOptionId;
	@JsonProperty
	private String menuOptionName;
	@JsonProperty
	private int menuOptionPrice;

	public MenuOption() {
	}

	public int getMenuId() {
		return menuId;
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
