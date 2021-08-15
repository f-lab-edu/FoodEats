package com.flab.foodeats.menu.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuEssentialParam {

	@JsonProperty
	private int menuId;

	@JsonProperty
	@NotBlank(message = "Input MenuName ")
	private String menuName;

	@JsonProperty
	@NotNull(message = "Input MenuPrice ")
	private int menuPrice;

	@JsonProperty
	@NotBlank(message = "Input MenuConfiguration")
	private String menuConfiguration;

	@JsonProperty
	@NotBlank(message = "Input MenuGroup ")
	private String menuGroup;

	public MenuEssentialParam() {
	}

	public MenuEssentialParam(int menuId, String menuName, int menuPrice, String menuConfiguration,
		String menuGroup) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuGroup = menuGroup;
	}

	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public String getMenuConfiguration() {
		return menuConfiguration;
	}

	public String getMenuGroup() {
		return menuGroup;
	}
}
