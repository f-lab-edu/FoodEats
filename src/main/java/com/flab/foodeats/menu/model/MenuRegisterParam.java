package com.flab.foodeats.menu.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuRegisterParam {

	@JsonProperty
	@NotNull(message = "Input menuId")
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

	// 옵션이 없으면 생략 가능
	private List<MenuOptionParam> menuOptionParams;

	public MenuRegisterParam() {
	}

	public MenuRegisterParam(int menuId, String menuName, int menuPrice, String menuConfiguration,
		String menuGroup, List<MenuOptionParam> menuOptionParams) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuGroup = menuGroup;
		this.menuOptionParams = menuOptionParams;
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

	public List<MenuOptionParam> getMenuOptionParams() {
		return menuOptionParams;
	}
}
