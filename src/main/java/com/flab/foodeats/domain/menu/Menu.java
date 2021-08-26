package com.flab.foodeats.domain.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu {

	@JsonProperty
	private int shopId;
	@JsonProperty
	private int menuId;
	@JsonProperty
	private String menuName;
	@JsonProperty
	private int menuPrice;
	@JsonProperty
	private String menuConfiguration;
	@JsonProperty
	private String menuExplanation;
	@JsonProperty
	private String menuGroup;

	public Menu() {
	}

	public Menu(String menuName, int menuPrice, String menuConfiguration, String menuExplanation, String menuGroup) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuExplanation = menuExplanation;
		this.menuGroup = menuGroup;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getMenuGroup() {
		return menuGroup;
	}
}
