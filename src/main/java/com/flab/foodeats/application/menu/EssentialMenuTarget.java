package com.flab.foodeats.application.menu;

public class EssentialMenuTarget {

	private String menuName;
	private int menuPrice;
	private String menuConfiguration;
	private String menuExplanation;
	private String menuGroup;

	public EssentialMenuTarget() {
	}

	public EssentialMenuTarget(String menuName, int menuPrice, String menuConfiguration, String menuExplanation,
		String menuGroup) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuExplanation = menuExplanation;
		this.menuGroup = menuGroup;
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
		return new Menu(menuName, menuPrice, menuConfiguration, menuExplanation, menuGroup);
	}
}
