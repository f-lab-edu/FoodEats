package com.flab.foodeats.domain.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuOption {

	private int menuId;
	private int menuOptionId;
	private String menuOptionName;
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
