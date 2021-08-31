package com.flab.foodeats.api.menu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.flab.foodeats.domain.menu.MenuOption;

public class OptionRequest {

	@NotBlank(message = "Input Option Name")
	private String menuOptionName;

	@NotNull(message = "Input Option Price")
	@Min(value = 100, message = "The minimum price is 100")
	private int menuOptionPrice;

	public OptionRequest() {
	}

	public OptionRequest(String menuOptionName, int menuOptionPrice) {
		this.menuOptionName = menuOptionName;
		this.menuOptionPrice = menuOptionPrice;
	}

	public String getMenuOptionName() {
		return menuOptionName;
	}

	public int getMenuOptionPrice() {
		return menuOptionPrice;
	}

	public MenuOption toEntity(int menuId) {
		return new MenuOption(menuId, menuOptionName, menuOptionPrice);
	}
}
