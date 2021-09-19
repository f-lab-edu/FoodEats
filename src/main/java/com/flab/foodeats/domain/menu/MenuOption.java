package com.flab.foodeats.domain.menu;


public class MenuOption {

	private Long menuId;
	private Long menuOptionId;
	private String menuOptionName;
	private int menuOptionPrice;

	public MenuOption() {
	}

	public MenuOption(Long menuId, String menuOptionName, int menuOptionPrice) {
		this.menuId = menuId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public MenuOption(Long menuId, Long menuOptionId, String menuOptionName, int menuOptionPrice) {
		this.menuId = menuId;
		this.menuOptionId = menuOptionId;
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public void updateOption(String menuOptionName, int menuOptionPrice) {
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public Long getMenuId() {
		return menuId;
	}

	public Long getMenuOptionId() {
		return menuOptionId;
	}

	public String getMenuOptionName() {
		return menuOptionName;
	}

	public int getMenuOptionPrice() {
		return menuOptionPrice;
	}
}
