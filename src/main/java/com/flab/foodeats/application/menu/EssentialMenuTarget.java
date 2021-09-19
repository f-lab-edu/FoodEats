package com.flab.foodeats.application.menu;

import com.flab.foodeats.domain.menu.Menu;

public class EssentialMenuTarget {

	private Long shopId;
	private Long menuId;
	private String menuName;
	private int menuPrice;
	private String menuConfiguration;
	private String menuExplanation;
	private String menuGroup;

	public EssentialMenuTarget() {
	}

	public EssentialMenuTarget(Long shopId, String menuName, int menuPrice, String menuConfiguration,
		String menuExplanation, String menuGroup) {
		this.shopId = shopId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuExplanation = menuExplanation;
		this.menuGroup = menuGroup;
	}

	public EssentialMenuTarget(Long shopId, Long menuId, String menuName, int menuPrice, String menuConfiguration,
		String menuExplanation, String menuGroup) {
		this.shopId = shopId;
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuExplanation = menuExplanation;
		this.menuGroup = menuGroup;
	}

	public Long getShopId() {
		return shopId;
	}

	public Long getMenuId() {
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

	public String getMenuExplanation() {
		return menuExplanation;
	}

	public String getMenuGroup() {
		return menuGroup;
	}

	public Menu toEntity() {
		return new Menu(shopId, menuId, menuName, menuPrice, menuConfiguration, menuExplanation, menuGroup);
	}
}
