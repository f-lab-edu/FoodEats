package com.flab.foodeats.api.menu;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EssentialMenuRequest {

	@NotBlank(message = "Input MenuName ")
	private String menuName;

	@NotNull(message = "Input MenuPrice ")
	private int menuPrice;

	@NotBlank(message = "Input MenuConfiguration")
	private String menuConfiguration;

	@NotBlank(message = "Input MenuExplanation")
	private String menuExplanation;

	@NotBlank(message = "Input MenuGroup ")
	private String menuGroup;

	public EssentialMenuRequest() {
	}

	public EssentialMenuRequest(String menuName, int menuPrice, String menuConfiguration, String menuExplanation,
		String menuGroup) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuConfiguration = menuConfiguration;
		this.menuExplanation = menuExplanation;
		this.menuGroup = menuGroup;
	}

	public EssentialMenuTarget toParam() {
		return new EssentialMenuTarget(menuName, menuPrice, menuConfiguration, menuExplanation, menuGroup);
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
}
