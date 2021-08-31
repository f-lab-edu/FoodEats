package com.flab.foodeats.domain.menu;


public class MenuOption {

	private int menuId;
	private int menuOptionId;
	private String menuOptionName;
	private int menuOptionPrice;

	public MenuOption() {
	}

	public MenuOption(int menuId, String menuOptionName, int menuOptionPrice) {
		this.menuId = menuId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public MenuOption(int menuId, int menuOptionId, String menuOptionName, int menuOptionPrice) {
		this.menuId = menuId;
		this.menuOptionId = menuOptionId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public void updateOption(String menuOptionName, int menuOptionPrice) {
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
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
