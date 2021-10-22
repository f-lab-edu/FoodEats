package com.flab.foodeats.api.menu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.flab.foodeats.application.menu.OptionTarget;
import com.flab.foodeats.domain.menu.MenuOption;

import lombok.Getter;

@Getter
public class OptionRequest {

	@NotBlank(message = "Input Option Name")
	private String menuOptionName;

	@NotNull(message = "Input Option Price")
	@Min(value = 100, message = "The minimum price is 100")
	private int menuOptionPrice;

	public OptionRequest() {
	}

	public OptionTarget toModifyParam(long menuOptionId, long menuId) {
		return OptionTarget.builder()
			.menuOptionId(menuOptionId)
			.menuId(menuId)
			.menuOptionName(menuOptionName)
			.menuOptionPrice(menuOptionPrice)
			.build();
	}

	public MenuOption toEntity(long menuId) {
		return MenuOption.builder()
			.menuId(menuId)
			.menuOptionName(menuOptionName)
			.menuOptionPrice(menuOptionPrice)
			.build();
	}
}
